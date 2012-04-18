package org.example;

import javax.ejb.Stateless;

@Stateless
public class HelloBean implements Hello
{
    @Override
    public String sayHello() {
        System.out.println("sayHello");
        
        return "Hello";
    }
}
