package org.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class HelloWorld {

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getHelloWorldJSON() {
		return "{\"result\":\"Hello World!\"}";
	}

	@GET
	@Path("/xml")
	@Produces({ "application/xml" })
	public String getHelloWorldXML() {
		return "<xml><result>Hello World!</result></xml>";
	}

}
