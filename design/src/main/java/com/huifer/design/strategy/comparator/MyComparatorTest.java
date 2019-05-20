package com.huifer.design.strategy.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Title : MyComparatorTest </p>
 * <p>Description : 比较器</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class MyComparatorTest {

    public static void main(String[] args) {
        // 人有高矮胖瘦
        peopleComparable();

        List<People> plist = new ArrayList<>();
        plist.add(new People(3, 3));
        plist.add(new People(1, 1));
        plist.add(new People(4, 4));
        plist.add(new People(2, 2));
        // 按照weight 排序
        plist.sort(sortWeight());
        // 按照height 排序
        plist.sort(sortHeight());

    }

    private static Comparator<People> sortWeight() {
        return new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return (int) (o1.weight - o2.weight);
            }
        };
    }

    private static Comparator<People> sortHeight() {
        return new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return (int) (o1.height - o2.height);
            }
        };
    }

    private static void peopleComparable() {
        List plist = new ArrayList<>();
        plist.add(new PeopleComparable(3, 3));
        plist.add(new PeopleComparable(1, 1));
        plist.add(new PeopleComparable(4, 4));
        plist.add(new PeopleComparable(2, 2));

        Collections.sort(plist);
        System.out.println(plist);
    }
}


