package org.example;

import javax.naming.*;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloBeanTest {

    private static InitialContext ic;
    private Hello hello;

    public HelloBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                  "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES,
                  "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost:1099");
        ic = new InitialContext(props);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        ic.close();
    }

    @Before
    public void setUp() {
        try {
            hello = (Hello)ic.lookup("HelloBean/remote");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSayHello_String() {
        assertTrue(hello.sayHello().equals("Hello"));
    }
}
