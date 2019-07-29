package org.example;

public class Counter {

    private int count = 0;

    public void addOne() {
        count++;
        System.out.println("[" + Thread.currentThread().getName() + "] count = " + count);
    }

    public int getCount() {
        return count;
    }
}

//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Counter {
//
//    private final AtomicInteger count = new AtomicInteger();
//
//    public void addOne() {
//        count.incrementAndGet();
//    }
//
//    public int getCount() {
//        return count.get();
//    }
//}