package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

@RunWith(ConcurrentTestRunner.class)
public class TestCounter {

    private Counter counter = new Counter();

    @Test
    public void addOne() {
        counter.addOne();
    }

    @After
    public void testCount() {
        assertEquals("4 Threads running addOne in parallel should lead to 4", 4, counter.getCount());
    }
}