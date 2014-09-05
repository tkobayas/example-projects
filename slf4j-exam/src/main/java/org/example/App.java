package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public void hello()
    {
        logger.debug("hello debug");
        logger.info("hello info");
        logger.error("hello error");
        
        System.out.println( "Hello World!" );
    }
}
