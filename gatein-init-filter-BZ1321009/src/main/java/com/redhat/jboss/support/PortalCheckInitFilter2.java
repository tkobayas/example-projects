package com.redhat.jboss.support;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.web.AbstractFilter;
import org.exoplatform.portal.application.PortalApplication;
import org.exoplatform.web.WebAppController;

/**
 * Improved version of org.gatein.portal.init.PortalCheckInitFilter to overcome BZ1321009
 *
 * @See https://bugzilla.redhat.com/show_bug.cgi?id=1321009
 */
public class PortalCheckInitFilter2 extends AbstractFilter {

    private static volatile boolean isPortalStarted = false;
    private static final String ERROR_MSG = "Server is starting, please try in a few seconds...";
    
    private static final int DEFAULT_DELAY = 10; // default delay in second 
    private static int delay = DEFAULT_DELAY;

    @Override
    public void afterInit(FilterConfig filterConfig) {
        String param = filterConfig.getInitParameter("delay");
        if (param != null) {
            try {
                delay = Integer.parseInt(param);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        if (!isPortalStarted) {
            ExoContainer container = getContainer();
            if (container != null) {
                if (container instanceof PortalContainer) {
                    PortalContainer portalContainer = (PortalContainer)container;
                    if (portalContainer.isStarted()) {
                        // isStarted is not enough (BZ1321009)
                        
                        // This logic potentially fixes the NullPointerException but doesn't guarantee the deployment completion anyway.
//                        WebAppController controller = (WebAppController) portalContainer.getComponentInstanceOfType(WebAppController.class);
//                        if (controller.getApplication(PortalApplication.PORTAL_APPLICATION_ID) != null) {
//                            System.out.println("isPortalStarted = true");
//                            isPortalStarted = true;
//                        }
                        
                        try {
                            Thread.sleep(delay * 1000);
                        } catch (InterruptedException e) {
                            // ignore
                        }
                        isPortalStarted = true;
                    }
                }
            }
            if (!isPortalStarted) {
                HttpServletResponse httpResp = (HttpServletResponse)resp;
                httpResp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, ERROR_MSG);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
     // nothing to do
    }

}