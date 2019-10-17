package com.huifer.jdk.jvm;

/**
 * <p>Title : IntFloat </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-04
 */
public class IntFloat {

    public static void main(String[] args) {
        Number number = true ? new Integer(1) : new Float(3);
        System.out.println(number);
    }

}
