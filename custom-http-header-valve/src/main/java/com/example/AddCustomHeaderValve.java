package com.example;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

public class AddCustomHeaderValve extends ValveBase {

    public void invoke(Request request, Response response) throws IOException, ServletException {
        (response.getResponse()).setHeader("X-Frame-Options", "SAMEORIGIN");
        getNext().invoke(request, response);
    }
}
