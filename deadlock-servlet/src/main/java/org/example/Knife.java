package org.example;

public class Knife {

    private static Knife singleton = new Knife();

    private Knife() {
    }

    public static Knife getInstance() {
        return singleton;
    }

    public void work() {
        System.out.println( "Knife!" );
    }
}
