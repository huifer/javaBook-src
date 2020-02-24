package com.huifer.jdk.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * 权重随机
 */
public class WeightRandom {



    public static void main(String[] args) {
        int[] weightArray = new int[]{1, 2, 3, 4, 5};
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        int key = 0;
        for (int weight : weightArray) {
            treeMap.put(key, weight);
            key += weight;
        }

        Random r = new Random();
        int num;
        int store;
        System.out.println("简单展示一下效果：");
        for (int i = 0; i < 10; i++) {
            num = r.nextInt(key);
            store = treeMap.floorEntry(num).getValue();
            System.out.printf("    num: %d, result: %d \n", num, store);
        }

        System.out.println("\n统计概率：");
        int[] storeStat = new int[weightArray.length + 1];
        for (int i = 0; i < 10000; i++) {
            num = r.nextInt(key);
            store = treeMap.floorEntry(num).getValue();
            storeStat[store]++;
        }

        for (int i = 1; i < storeStat.length; i++) {
            double result = storeStat[i] / 10000.0;
            System.out.printf("    store %d: %f\n", i, result);
        }

    }

}
