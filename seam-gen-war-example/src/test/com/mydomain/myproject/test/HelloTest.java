package com.mydomain.myproject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class HelloTest extends SeamTest {

	@Test
	public void test_hello() throws Exception {
		new FacesRequest("/hello.xhtml") {
			@Override
			protected void updateModelValues() throws Exception {				
				//set form input to model attributes
				setValue("#{hello.value}", "seam");
			}
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{hello.hello}");
			}
			@Override
			protected void renderResponse() {
				//check model attributes if needed
				assert getValue("#{hello.value}").equals("seam");
			}
		}.run();
	}
}
