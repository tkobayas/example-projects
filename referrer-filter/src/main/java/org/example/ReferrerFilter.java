package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *   $JBOSS_HOME/gatein/gatein.ear/portal.war/WEB-INF/web.xml example
 * 
 *   <filter>
 *    <filter-name>ReferrerFilter</filter-name>
 *    <filter-class>org.example.ReferrerFilter</filter-class>
 *    <init-param>
 *      <param-name>accept-referrer-domains</param-name>
 *      <param-value>localhost,127.0.0.1</param-value>
 *    </init-param>
 *    <init-param>
 *      <param-name>exceptional-paths</param-name>
 *      <param-value>/portal/classic,/portal/classic/home</param-value>
 *    </init-param>
 *  </filter>
 *
 *  <filter-mapping>
 *    <filter-name>ReferrerFilter</filter-name>
 *    <url-pattern>/*</url-pattern>
 *  </filter-mapping>
 */
public class ReferrerFilter implements Filter {

    private List<String> acceptedReferrerDomains = new ArrayList<String>();
    private List<String> exceptionalPaths = new ArrayList<String>();

    public void init(FilterConfig filterConfig) throws ServletException {
        String initParamDomains = filterConfig.getInitParameter("accept-referrer-domains");

        if (initParamDomains != null && !initParamDomains.isEmpty()) {
            String[] domains = initParamDomains.split(",");
            acceptedReferrerDomains = Arrays.asList(domains);
        }
        System.out.println("##### acceptedReferrerDomains = " + acceptedReferrerDomains);

        String initParamPaths = filterConfig.getInitParameter("exceptional-paths");

        if (initParamPaths != null && !initParamPaths.isEmpty()) {
            String[] paths = initParamPaths.split(",");
            exceptionalPaths = Arrays.asList(paths);
        }
        System.out.println("##### exceptionalPaths = " + exceptionalPaths);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("-------- before filter");
        String referrer = ((HttpServletRequest) request).getHeader("referer");
        System.out.println("referrer = " + referrer);

        String referrerDomain = null;
        if (referrer != null) {
            Matcher m = Pattern.compile("https?://([^/:]+)[/:].*").matcher(referrer);
            if (m.find()) {
                referrerDomain = m.group(1);
            } else {
                referrerDomain = null;
            }
        }
        System.out.println("referrerDomain = " + referrerDomain);
        
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (requestURI.endsWith("/")) {
            requestURI = requestURI.substring(0, requestURI.length() - 1);
        }
        System.out.println("requestURI = " + requestURI);

        if (exceptionalPaths.contains(requestURI)) {
            System.out.println("Don't check referrer as it's an exceptional path");
        } else {
            if (referrerDomain != null && acceptedReferrerDomains.contains(referrerDomain)) {
                System.out.println("It's accepted domain");
            } else {
                System.out.println("!!!!");
                throw new ServletException("Accessed from a different domain : " + referrerDomain);
            }
        }

        chain.doFilter(request, response);
        System.out.println("------- after filter");
    }

}
