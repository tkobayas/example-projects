package com.redhat.jboss.support;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.exoplatform.container.web.AbstractFilter;

/**
 * Improved version of org.gatein.portal.init.PortalCheckInitFilter to overcome BZ1321009
 *
 * @See https://bugzilla.redhat.com/show_bug.cgi?id=1321009
 */
public class PortalCheckInitFilter2 extends AbstractFilter {

    private static volatile boolean isPortalStarted = false;
    private static final String ERROR_MSG = "Server is starting, please try in a few seconds...";
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        if (!isPortalStarted) {

            try {
                String urlString = System.getProperty("jmx.service.url", "service:jmx:remoting-jmx://localhost:9999");

                JMXServiceURL serviceURL = new JMXServiceURL(urlString);
                JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
                MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

                ObjectName objectName = new ObjectName("jboss.as:management-root=server");
                
                String value = (String)connection.getAttribute(objectName, "serverState");
                if (value != null && value.equals("running")) {
                    isPortalStarted = true;
                }

                jmxConnector.close();
            } catch (Exception e) {
                e.printStackTrace();
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