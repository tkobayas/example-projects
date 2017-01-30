package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyContainer {

    private List<String> valueList = new ArrayList<String>();

    public void doWork() {

        for (int i = 0; i < 1500000; i++) {
            valueList.add("abc" + i);
        }
        System.out.println("valueList.size() = " + valueList.size());
    }
}
