package org.jboss.as.quickstarts.helloworld.qualifier;

import javax.enterprise.inject.Default;

@First
public class HelloServiceImpl implements HelloService {

    public String createHelloMessage(String name) {
        return "Hello " + name + " Impl !";
    }

}
