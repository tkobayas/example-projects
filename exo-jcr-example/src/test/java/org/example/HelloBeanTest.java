package org.example;

import javax.naming.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
    public void testSayHello() {
    	
    	hello.sayHello("john");
    	
        assertTrue(true);
    }

    @Ignore
    public void testSayHello_String() {
    	
    	ExecutorService executor1 = Executors.newSingleThreadExecutor();
    	ExecutorService executor2 = Executors.newSingleThreadExecutor();
    	
    	for (int i = 0; i < 1; i ++) {
			executor1.execute(new HelloTask("root"));
			executor2.execute(new HelloTask("john"));
    	}
    	
    	executor1.shutdown();
    	executor2.shutdown();
    	try {
			executor1.awaitTermination(30, TimeUnit.SECONDS);
			executor2.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertTrue(true);
    }
    
    class HelloTask implements Runnable {
    	
    	private String user;
    	
    	public HelloTask(String user) {
    		this.user = user;
    	}
    	
    	public void run() {
    		hello.sayHello(user);
    	}
    }
}
