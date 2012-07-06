package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String PAGE_HEADER = "<html><head/><body>";
	private static String PAGE_FOOTER = "</body></html>";

	@EJB
	private HelloLocal helloService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String result = "???";
		try {
			long processInstanceId = helloService.startProcess();
			result = helloService.retrieveTaskByJohn();
			result = helloService.retrieveTaskByMary();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<h1>" + result + "</h1>");
		writer.println(PAGE_FOOTER);
		writer.close();
	}
}