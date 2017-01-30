package org.example;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    public void testApp() {
        for (int i = 0; i < 1000; i++) {
            MyContainer app = new MyContainer();
            app.doWork();

            app = null;
        }

        assertTrue(true);
    }
}
