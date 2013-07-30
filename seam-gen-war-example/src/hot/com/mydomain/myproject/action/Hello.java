package com.mydomain.myproject.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;
import org.hibernate.validator.Length;

@Name("hello")
public class Hello
{
    @Logger private Log log;

    @In StatusMessages statusMessages;

    private String value;

    public void hello()
    {
        // implement your business logic here
        log.info("hello.hello() action called with: #{hello.value}");
        statusMessages.add("hello #{hello.value}");
    }

    // add additional action methods
  
    @Length(max = 10)
    public String getValue()
    {
        return value;
    }
  
    public void setValue(String value)
    {
        this.value = value;
    }
  
}
