package org.example;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       InetAddress localHost = InetAddress.getLocalHost();
       System.out.println(localHost);
       System.out.println(localHost.getHostAddress());
       
       System.out.println("req.getLocalAddr() = " + req.getLocalAddr());
       
       getAppStatus("http://localhost:8080/dashbuilder/workspace/jbpm-dashboard");
       
       
      PrintWriter writer = resp.getWriter();
      writer.println(PAGE_HEADER);
      writer.println("<h1>Hello, world!</h1>");
      writer.println(PAGE_FOOTER);
      writer.close();
   }

   public void getAppStatus(String theUrl) {
       // Get a list of the urls to check for the given url
       List<String> urls = explodeUrl(theUrl);
       Exception exc = null;
       for (String anUrl : urls) {
           System.out.println("anUrl = " + anUrl);
           try {
               // Check whether the service is available
               int status = pingUrl(anUrl);
               System.out.println(status);
               return;
           } catch (Exception e){
               System.out.println(e);
               exc = e;
           }
       }
       if (exc != null) exc.printStackTrace();
   }

   protected int pingUrl(String anUrl) throws Exception {
       URL url = new URL(anUrl);
       HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
       return urlConnection.getResponseCode();
   }

   protected List<String> explodeUrl(String anUrl) {
       List<String> results = new ArrayList<String>();

       // Add the target URL
       results.add(anUrl);

       // Add the "localhost" version
       try {
           String host = new URL(anUrl).getHost();
           String localHost = InetAddress.getLocalHost().getHostAddress();
           if (!host.equals(localHost)) {
               results.add(anUrl.replace(host, localHost));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       // Add jboss.bind.address
       try {
           String host = new URL(anUrl).getHost();
           String bindAddress = System.getProperty("jboss.bind.address");
           if (bindAddress != null && !host.equals(bindAddress)) {
               results.add(anUrl.replace(host, bindAddress));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return results;
   }
}
