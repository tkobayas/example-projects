package org.jboss.as.quickstarts.helloworld.produces;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MyProducer {

    @Produces @Random
    public HelloService getHelloService() {
        return new HelloServiceImpl2();
    }
}
