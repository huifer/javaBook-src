package com.huifer.jdk.jdk8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 * 方法引用
 *
 * @author huifer
 * @date 2019-06-12
 */
public class Demo02 {
    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 2, 3);
        lists.forEach(
                integer -> System.out.println(integer)
        );

        lists.forEach(System.out::println);
    }
}
