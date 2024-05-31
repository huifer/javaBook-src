package com.huifer.jdk.jdk8.stearm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-16
 */
public class Demo02 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        // 转换成string数组
        // 方法引用
        String[] strings = stringStream.toArray(String[]::new);

        System.out.println(strings);

        List<String> collect = Arrays.stream(strings).collect(Collectors.toList());


        ArrayList<String> collect1 = Arrays.stream(strings).collect(
                () -> new ArrayList<String>(), // 返回类型
                (list, item) -> list.add(item), // 将item传入list中
                (result, list) -> result.addAll(list) // 将list全部追加到result中返回
        );

        System.out.println(collect1);

        ArrayList<String> collect2 = Arrays.stream(strings).collect(
                ArrayList::new, // 返回类型
                ArrayList::add, // 将item传入list中
                ArrayList::addAll // 将list全部追加到result中返回
        );

        System.out.println(collect2);
    }
}
