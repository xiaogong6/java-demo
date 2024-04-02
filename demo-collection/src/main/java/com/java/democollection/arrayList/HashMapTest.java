package com.java.democollection.arrayList;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-01
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = Maps.newHashMapWithExpectedSize(7);

        List<String> list = Arrays.asList("1", "2", "3");

        Stream<String> stream = list.stream();

        Stream<String> stringStream = list.parallelStream();

        // stringStream.reduce()

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
    }

}
