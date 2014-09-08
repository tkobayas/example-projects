package org.jboss.as.quickstarts.helloworld;

public class HelloServiceImpl implements HelloService {

    public String createHelloMessage(String name) {
        return "Hello " + name + " Impl !";
    }

}
