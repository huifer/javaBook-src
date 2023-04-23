package com.huifer.jdk.jdk8.stearm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * steam 01
 *
 * @author huifer
 * @date 2019-06-12
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> collect = list.stream().map(i -> i + 1).collect(Collectors.toList());
        System.out.println(collect);
        List<Double> collect2 = list.stream().map(
                Demo01::apply
        ).collect(Collectors.toList());
        System.out.println(collect2);

    }

    private static Double apply(Integer i) {
        return Math.pow(i, i);
    }
}
