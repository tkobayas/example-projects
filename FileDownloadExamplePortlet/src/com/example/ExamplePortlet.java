package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.UnavailableException;

public class ExamplePortlet extends GenericPortlet {

    /**
     * Default constructor. 
     */
    public ExamplePortlet() {
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException, UnavailableException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("Hello World!");
		writer.write("<html><head></head><body><a href=\"/FileDownloadExample/Download.do?FileId=XXX.pdf\">Download</a></body></html>");
		writer.close();
	}
}
