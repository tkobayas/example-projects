package org.example;

public class Fork {

    private static Fork singleton = new Fork();

    private Fork() {
    }

    public static Fork getInstance() {
        return singleton;
    }

    public void work() {
        System.out.println( "Fork!" );
    }
}
