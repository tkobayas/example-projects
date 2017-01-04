package com.github.tkobayas.jmh.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ArrayListBenchmark {
    private static final int size = 100;

    @Benchmark
    public void withInitialSize() {
        List<Integer> sizedList = new ArrayList<>( size );
        for ( int i = 0; i < size; i++ ) {
            sizedList.add( i );
        }
    }

    @Benchmark
    public void withoutInitialSize() {
        List<Integer> defaultList = new ArrayList<>();
        for ( int i = 0; i < size; i++ ) {
            defaultList.add( i );
        }
    }

    public static void main( String[] args ) throws RunnerException, IOException {
        Options opt = new OptionsBuilder().include( ArrayListBenchmark.class.getSimpleName() ).warmupIterations( 5 ).measurementIterations( 5 ).forks( 2 )
                .build();

        new Runner( opt ).run();
    }
}