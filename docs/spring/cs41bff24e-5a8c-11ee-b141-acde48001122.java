package com.huifer.jdk.jdk8.stearm;

import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-16
 */
public class Demo04 {
    public static void main(String[] args) {
//        Stream<String> stringStream = Stream.generate(UUID.randomUUID()::toString);
        Stream<String> stringStream = null;

        stringStream.findFirst().get();

        if (stringStream.findFirst().isPresent()) {
            // TODO:.....
        }
        stringStream.findFirst().ifPresent(System.out::println);

    }
}
