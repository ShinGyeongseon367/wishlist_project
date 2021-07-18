package com.example.restaurant.db;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryDbRepositoryAbstractTest {

    @Test
    public void streamBuilderTest() {
        Stream<Object> build = Stream.builder().add("Temp").build();

        Stream<String> limit = Stream.generate(() -> "temp").limit(5);
//        List<String> collect = limit.collect(Collectors.toList());

//        System.out.println(Arrays.toString(collect.toArray()));

//        Stream<String> stream = collect.stream();

        long count = limit.count();
        System.out.println("count ::: " + count);

        Stream<String> limit1 = Stream.generate(() -> "temp").limit(5);




    }

}