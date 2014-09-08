package org.jboss.as.quickstarts.helloworld.produces;

public class HelloServiceImpl2 implements HelloService {
    
    public HelloServiceImpl2() {
        // TODO Auto-generated constructor stub
    }

    public String createHelloMessage(String name) {
        return "Hello " + name + " Impl2 !";
    }

}
