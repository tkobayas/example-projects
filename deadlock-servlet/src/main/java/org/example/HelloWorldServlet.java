package org.example;

import java.io.IOException;
import java.io.PrintWriter;

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
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        if ( req.getParameter( "cmd" ).equals( "knifeAndFork" ) ) {
            knifeAndFork();
        } else if ( req.getParameter( "cmd" ).equals( "forkAndKnife" ) ) {
            forkAndKnife();
        }

        PrintWriter writer = resp.getWriter();
        writer.println( PAGE_HEADER );
        writer.println( "<h1>Hello, Deadlock!</h1>" );
        writer.println( PAGE_FOOTER );
        writer.close();
    }

    private void forkAndKnife() {
        Fork fork = Fork.getInstance();
        synchronized ( fork ) {
            fork.work();
            
            try {
                Thread.sleep( 5000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            Knife knife = Knife.getInstance();
            synchronized ( knife ) {
                knife.work();
            }
            
        }
    }

    private void knifeAndFork() {
        Knife knife = Knife.getInstance();
        synchronized ( knife ) {
            knife.work();
            
            try {
                Thread.sleep( 5000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            Fork fork = Fork.getInstance();
            synchronized ( fork ) {
                fork.work();
            }
            
        }
    }

}
