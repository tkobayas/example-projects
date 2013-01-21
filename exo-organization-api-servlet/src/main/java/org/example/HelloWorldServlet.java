package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.services.organization.Group;
import org.exoplatform.services.organization.GroupHandler;
import org.exoplatform.services.organization.OrganizationService;

public class HelloWorldServlet extends HttpServlet {

    static String PAGE_HEADER = "<html><head /><body>";

    static String PAGE_FOOTER = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        System.out.println(checkOrgApi());

        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println("<h1>Hello, world!</h1>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

    public Group checkOrgApi() {
        try {

            InitialContext ic = new InitialContext();
            UserTransaction ut =
                (UserTransaction)ic.lookup("UserTransaction");
            ut.begin();
											
            // do the op on the service
            OrganizationService service = (OrganizationService) PortalContainer
                .getInstance().getComponentInstanceOfType(
                    OrganizationService.class);

            GroupHandler grpHandler = service.getGroupHandler();
            // Group result = grpHandler.findGroupById("/platform/" +
            // System.currentTimeMillis());
            Group result = grpHandler.findGroupById("/platform");
			
            ut.commit();
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
