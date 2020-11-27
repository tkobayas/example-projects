package org.example;

import java.io.IOException;
import java.util.Collection;

import com.brein.time.timeintervals.collections.ListIntervalCollection;
import com.brein.time.timeintervals.indexes.IntervalTree;
import com.brein.time.timeintervals.indexes.IntervalTreeBuilder;
import com.brein.time.timeintervals.indexes.IntervalTreeBuilder.IntervalType;
import com.brein.time.timeintervals.intervals.IInterval;
import com.brein.time.timeintervals.intervals.LongInterval;

public class App01 {

    public static void main(String[] args) throws IOException {
        final IntervalTree tree = IntervalTreeBuilder.newBuilder()
                                                     .usePredefinedType(IntervalType.LONG)
                                                     .collectIntervals(interval -> new ListIntervalCollection())
                                                     .build();
        tree.add(new LongInterval(1L, 5L));
        tree.add(new LongInterval(2L, 5L));
        tree.add(new LongInterval(3L, 5L));

        final Collection<IInterval> overlap = tree.overlap(new LongInterval(2L, 2L));
        overlap.forEach(System.out::println); // will print out [1, 5] and [2, 5]

        System.out.println("---------");

        final Collection<IInterval> find = tree.find(new LongInterval(2L, 5L));
        find.forEach(System.out::println); // will print out only [2, 5]
    }
}
