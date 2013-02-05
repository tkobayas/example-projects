package org.example;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.api.domain.entity.PlanningEntity;
import org.drools.planner.api.domain.variable.PlanningVariable;
import org.drools.planner.api.domain.variable.ValueRange;
import org.drools.planner.api.domain.variable.ValueRangeType;

@PlanningEntity(difficultyComparatorClass = ItemDifficultyComparator.class)
public class Item {

    private String name;
    
    private int price;
    
    private Bucket bucket;
    
    public Item () {
        
    }
            
    public Item (String name, int price) {
        this.name = name;
        this.price = price;
    }

    @PlanningVariable()
    @ValueRange(type = ValueRangeType.FROM_SOLUTION_PROPERTY, solutionProperty = "bucketList")
    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public boolean isInCart() {
        return bucket == null ? false : bucket.isCart();
    }
    
    public Item clone() {
        Item clone = new Item();
        clone.name = name;
        clone.price = price;
        clone.bucket = bucket;
        return clone;
    }
    
    public boolean solutionEquals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Item) {
            Item other = (Item) o;
            return new EqualsBuilder()
                    .append(bucket, other.bucket)
                    .isEquals();
        } else {
            return false;
        }
    }
    
    public int solutionHashCode() {
        return new HashCodeBuilder()
                .append(bucket)
                .toHashCode();
    }
}
