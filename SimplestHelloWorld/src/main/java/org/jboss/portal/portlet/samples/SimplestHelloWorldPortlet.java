/******************************************************************************
 * JBoss, a division of Red Hat                                               *
 * Copyright 2008, Red Hat Middleware, LLC, and individual                    *
 * contributors as indicated by the @authors tag. See the                     *
 * copyright.txt in the distribution for a full listing of                    *
 * individual contributors.                                                   *
 *                                                                            *
 * This is free software; you can redistribute it and/or modify it            *
 * under the terms of the GNU Lesser General Public License as                *
 * published by the Free Software Foundation; either version 2.1 of           *
 * the License, or (at your option) any later version.                        *
 *                                                                            *
 * This software is distributed in the hope that it will be useful,           *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU           *
 * Lesser General Public License for more details.                            *
 *                                                                            *
 * You should have received a copy of the GNU Lesser General Public           *
 * License along with this software; if not, write to the Free                *
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA         *
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.                   *
 ******************************************************************************/
package org.jboss.portal.portlet.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

public class SimplestHelloWorldPortlet extends GenericPortlet
{
    public void doView(RenderRequest request, RenderResponse response) throws IOException
    {
    	System.out.println("getContextPath : " + request.getContextPath());

    	System.out.println("getAttribute : " + request.getAttribute("zzz"));
    	
        PrintWriter writer = response.getWriter();
        writer.print("dept = " + request.getParameter("dept"));
        writer.print("<br>");

        PortletURL renderUrl = response.createRenderURL();
        renderUrl.setParameter("dept", "aaa");
        writer.print("<A HREF=\"");
        renderUrl.write(writer);
        writer.print("\">RenderURL link with dept param</A>");
        writer.print("<br>");

        PortletURL actionUrl = response.createActionURL();
        writer.print("<A HREF=\"");
        actionUrl.write(writer);
        writer.print("\">ActionURL link without param</A>");
        writer.print("<br>");

        writer.close();
    }

    public void processAction(ActionRequest request, ActionResponse response) throws PortletException
    {
        System.out.println("processAction!");
        request.setAttribute("zzz", "xxx");
    }


}

