package com.huifer.jdk.jdk8.stearm;

import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-16
 */
public class Demo06 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4).map(i -> {
            System.out.println("中间方法调用");
            return i;
        });
//                .collect(Collectors.toList());


//                .forEach(System.out::println);
    }
}
