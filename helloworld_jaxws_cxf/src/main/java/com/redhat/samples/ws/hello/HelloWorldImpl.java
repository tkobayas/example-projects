package com.redhat.samples.ws.hello;

import javax.jws.WebService;

@WebService(endpointInterface = "com.redhat.samples.ws.hello.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

  @Override
  public String sayGoodbye(String toWhom) {
    return String.format("Goodbye, %s!", toWhom);
  }

  @Override
  public String sayHello(String toWhom) {
    return String.format("Hello, %s!", toWhom);
  }
}
