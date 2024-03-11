package com.huifer.jdk.jdk8.stearm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-16
 */
public class Demo03 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        ArrayList<String> collect = stringStream.collect(Collectors.toCollection(ArrayList::new));
        HashSet<String> collect1 = stringStream.collect(Collectors.toCollection(HashSet::new));
    }
}
