package org.example;

import javax.ejb.*;

@Remote
public interface Hello
{
    public String sayHello();
}
