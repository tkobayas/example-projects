package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletShutdownHook extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletShutdownHook() {
        super();

    }

    public void init() {
        MyShutdown sh = new MyShutdown(this);
        Runtime.getRuntime().addShutdownHook(sh);
        System.out.println("Added shutdown hook");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    class MyShutdown extends Thread {

        public MyShutdown(ServletShutdownHook managedClass) {
            super();
            this.managedClass = managedClass;
        }

        private ServletShutdownHook managedClass;

        public void run() {
            System.out.println("MyShutDown Thread started");
            try {
                managedClass.freeResources();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void freeResources() {
        System.out.println("####################### Freeing resources here!");
        callKieServer();

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        callKieServer();
        
        System.out.println("####################### done");

    }

    private void callKieServer() {
        try {
            URL url = new URL("http://127.0.0.1:8080/kie-server/services/rest/server");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String userpassword = "kieserver:kieserver1!";
            String authorization = "Basic " + Base64.getEncoder().encodeToString(userpassword.getBytes());
            conn.setRequestProperty("Authorization", authorization);
            conn.connect();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}