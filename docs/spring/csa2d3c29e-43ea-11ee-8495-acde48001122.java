package com.huifer.jdk.hashcode;

/**
 * <p>Title : Run </p>
 * <p>Description : hashCode相同 equlas就是ture吗？</p>
 *
 * @author huifer
 * @date 2019-05-24
 */
public class Run {

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = new String("123");
        System.out.println(String.format("s1: %d \t s2: %d", s1.hashCode(), s2.hashCode()));

        System.out.println(s1.equals(s2));
    }
}
