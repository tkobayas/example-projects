package org.jboss.as.quickstarts.helloworld.qualifier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/HelloWorldQ")
public class HelloWorldServlet extends HttpServlet {

    static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

    static String PAGE_FOOTER = "</body></html>";

    @Inject
    @First
    HelloService helloService;
    
    @Inject
    @Second
    HelloService helloService2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println("<h1>" + helloService.createHelloMessage("World") + "</h1>");
        writer.println("<h1>" + helloService2.createHelloMessage("World") + "</h1>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}
