package org.example;

import javax.ejb.Stateless;

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

@Stateless
public class HelloBean implements Hello
 {
     @Override
     public String sayHello() {
         Group group = findGroup();

         System.out.println(group);
        
         return "Hello";
     }

     public Group findGroup() {

         RequestLifeCycle.begin(PortalContainer.getInstance());

         try {

             OrganizationService service = (OrganizationService) PortalContainer
                 .getInstance().getComponentInstanceOfType(
                     OrganizationService.class);

             GroupHandler grpHandler = service.getGroupHandler();
             // Group result = grpHandler.findGroupById("/platform/" +
             // System.currentTimeMillis());
             Group result = grpHandler.findGroupById("/platform");
			
             return result;
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             return null;
         } finally {
             RequestLifeCycle.end();
         }
     }

}
