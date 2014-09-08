package org.jboss.as.quickstarts.helloworld.qualifier;

@Second
public class HelloServiceImpl2 implements HelloService {

    public String createHelloMessage(String name) {
        return "Hello " + name + " Impl2 !";
    }

}
