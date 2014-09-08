package org.jboss.as.quickstarts.helloworld.produces;


public class HelloServiceImpl implements HelloService {
    
    public HelloServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    public String createHelloMessage(String name) {
        return "Hello " + name + " Impl !";
    }

}
