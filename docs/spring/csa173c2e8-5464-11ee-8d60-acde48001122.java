package com.huifer.design.prototype;

/**
 * <p>Title : PrototypeTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class PrototypeTest {

    public static void main(String[] args) throws Exception {
        Prototype prototype = new Prototype();
        prototype.name = "张三";
        prototype.tag = new Tag("123");

        Prototype clone = (Prototype) prototype.clone();

        clone.tag.f = "asasas";
        System.out.println(clone.tag.f);
        System.out.println(prototype.tag.f);

        System.out.println(clone.tag);
        System.out.println(prototype.tag);

        System.out.println(prototype.name == clone.name);
        System.out.println(prototype.tag == clone.tag);


    }

}
