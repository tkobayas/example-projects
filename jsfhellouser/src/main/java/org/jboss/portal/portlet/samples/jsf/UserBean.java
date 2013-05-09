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
package org.jboss.portal.portlet.samples.jsf;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponseWrapper;

import org.jboss.portal.portlet.impl.jsr168.api.ActionResponseImpl;

/**
 * @author <a href="mailto:theute@jboss.org">Thomas Heute</a>
 * @version $Revision$
 */
public class UserBean
{

   private String userName;

   public String getUserName()
   {
      return userName;
   }

   public void setUserName(String userName)
   {
      this.userName = userName;
   }


   public void downLoadFile(ActionEvent event){
   		System.out.println(" invoke downLoadFile ....");
   		
   		
   		FacesContext fc = FacesContext.getCurrentInstance();
   		ExternalContext ec = fc.getExternalContext();
   		ActionResponseImpl res = (ActionResponseImpl) ec.getResponse();
   		
   		HttpServletResponseWrapper response = res.getRealResponse();
   		response.reset();
   		
   		//MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
   		//String contentType = mimeTypesMap.getContentType(fileName);
   		response.setContentType("text/x-vcard; charset=UTF-8");
   		response.setContentLength(100);
   		response.setHeader("Content-Disposition", "attachment; filename=\"AAAA\"");
   		try {
   			ServletOutputStream os = response.getOutputStream();
   			os.write(new byte[] {55,55,55});
   			os.flush();
   			os.close();
   			System.out.println("download  completed ........");
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   		
   		fc.responseComplete();
   	}
}

