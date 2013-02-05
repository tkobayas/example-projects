package org.example;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

public class ItemDifficultyComparator implements Comparator<Item>, Serializable {
    
    public int compare(Item a, Item b) {
        return new CompareToBuilder()
                .append(a.getPrice(), b.getPrice())
                .toComparison();
    }
}
