package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.api.domain.solution.PlanningEntityCollectionProperty;
import org.drools.planner.core.score.buildin.simple.SimpleScore;
import org.drools.planner.core.solution.Solution;


public class WishListSolution implements Solution<SimpleScore> {
    
    private SimpleScore score;
    
    private List<Item> itemList;
    
    private List<Bucket> bucketList;

    @PlanningEntityCollectionProperty
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Bucket> getBucketList() {
        return bucketList;
    }

    public void setBucketList(List<Bucket> bucketList) {
        this.bucketList = bucketList;
    }

    @Override
    public SimpleScore getScore() {
        return score;
    }

    @Override
    public void setScore(SimpleScore score) {
        this.score = score;
    }

    @Override
    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(bucketList);
        return facts;
    }

    @Override
    public Solution<SimpleScore> cloneSolution() {
        WishListSolution clone = new WishListSolution();
        clone.bucketList = bucketList;
        List<Item> clonedItemList = new ArrayList<Item>(
                itemList.size());
        for (Item item : itemList) {
            Item clonedItem = item.clone();
            clonedItemList.add(clonedItem);
        }
        clone.itemList = clonedItemList;
        clone.score = score;
        return clone;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WishListSolution)) {
            return false;
        } else {
            WishListSolution other = (WishListSolution) o;
            if (itemList.size() != other.itemList.size()) {
                return false;
            }
            for (Iterator<Item> it = itemList.iterator(), otherIt = other.itemList.iterator(); it.hasNext();) {
                Item item = it.next();
                Item otherItem = otherIt.next();
                // Notice: we don't use equals()
                if (!item.solutionEquals(otherItem)) {
                    return false;
                }
            }
            return true;
        }
    }

    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        for (Item item : itemList) {
            // Notice: we don't use hashCode()
            hashCodeBuilder.append(item.solutionHashCode());
        }
        return hashCodeBuilder.toHashCode();
    }
}
