package com.redhat.jboss.support;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
 * This filter is designed to avoid concurrency against UserSite creation. See BZ1059053
 */
public class DashboardConcurrencyFilter implements Filter {

    private static Set<String> userSiteCreationInProgress = Collections.synchronizedSet(new HashSet<String>());
    private static Set<String> userSiteCreated = Collections.synchronizedSet(new HashSet<String>());

    private static final int DEFAULT_MAX_RETRY = 100;
    private static int maxRetry = DEFAULT_MAX_RETRY;

    public void init(FilterConfig filterConfig) {
        try {
            maxRetry = Integer.parseInt(filterConfig.getInitParameter("maxRetry"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        String user = getUser(request);

        if (user == null) {
            // ignore
            chain.doFilter(request, response);
            return;
        }

        if (userSiteCreated.contains(user)) {
            // Once user site is created, concurrent access is safe.
            // After restarting JBoss, this Set gets empty but will be populated by the first try
            //System.out.println("already created for : " + user);
            chain.doFilter(request, response);
            return;
        }

        if (!userSiteCreationInProgress.contains(user)) {
            // this means the first access for user site
            //System.out.println("first access by " + user);
            userSiteCreationInProgress.add(user);
            try {
                chain.doFilter(request, response);
            } finally {
                userSiteCreationInProgress.remove(user);
            }
            userSiteCreated.add(user);
            return;
        }

        // Now avoid concurrent access
        //System.out.println("Start waiting : " + System.currentTimeMillis());
        for (int i = 0; i < maxRetry; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (!userSiteCreationInProgress.contains(user)) {
                break;
            }
            if (i == (maxRetry -1)) {
                System.out.println("WARN: Reached to maxRetry (" + maxRetry + "). This could be a slowness issue in Portal. We recommend to capture several thread dumps." );
                userSiteCreationInProgress.remove(user);
            }
        }
        //System.out.println("Finish waiting : " + System.currentTimeMillis());

        chain.doFilter(request, response);
        return;
    }

    private String getUser(ServletRequest request) {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String servletPath = httpReq.getServletPath();
        String[] tokens = servletPath.split("/");
        if (tokens.length < 3) {
            return null;
        }

        // for example, /u/john/ -> token[2] == "john"
        return tokens[2];
    }
}
