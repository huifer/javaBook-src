package com.huifer.jdk.jdk8.stearm;

import java.util.stream.Stream;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-16
 */
public class Demo05 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.iterate(1, item -> item + 1).limit(10);

        int sum = integerStream.filter(integer -> integer > 2).mapToInt(x -> x * 2).skip(2).limit(2).sum();
        System.out.println(sum);

//        IntSummaryStatistics intSummaryStatistics = distinct.filter(integer -> integer > 2).mapToInt(x -> x * 2).summaryStatistics();
//        System.out.println(intSummaryStatistics.getMin());
//

    }
}
