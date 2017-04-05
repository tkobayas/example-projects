package com.sample;

import java.io.Serializable;

public class MyPojo implements Serializable {

    private String name;

    public MyPojo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
