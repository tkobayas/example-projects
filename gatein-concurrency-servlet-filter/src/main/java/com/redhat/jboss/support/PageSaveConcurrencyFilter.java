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
 * This filter is designed to avoid concurrency against page save. See BZ
 */
public class PageSaveConcurrencyFilter implements Filter {

    private static Set<String> pageSaveInProgress = Collections.synchronizedSet(new HashSet<String>());

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

        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();

        if ("UIPageEditor".equals(req.getParameter("portal:componentId"))
                && "Finish".equals(req.getParameter("portal:action"))) {
            // this means the page is being saved
            //System.out.println("page is being saved for " + servletPath);
            pageSaveInProgress.add(servletPath);
            try {
                chain.doFilter(request, response);
                //System.out.println("page save has been committed for " + servletPath);
            } finally {
                pageSaveInProgress.remove(servletPath);
            }
            return;
        }

        // Now avoid concurrent access
        for (int i = 0; i < maxRetry; i++) {
            if (!pageSaveInProgress.contains(servletPath)) {
                break;
            }
            
            //System.out.println("waiting for " + servletPath);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            if (i == (maxRetry - 1)) {
                System.out.println("WARN: Reached to maxRetry (" + maxRetry
                        + "). This could be a slowness issue in Portal. We recommend to capture several thread dumps.");
                pageSaveInProgress.remove(servletPath);
            }
        }

        chain.doFilter(request, response);
        return;
    }

}
