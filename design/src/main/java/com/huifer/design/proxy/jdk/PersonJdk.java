package com.huifer.design.proxy.jdk;

/**
 * <p>Title : PersonJdk </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class PersonJdk implements ZhiYuan {

    @Override
    public void findWork() {
        System.out.println("找工作");
    }

    @Override
    public void findHouse() {
        System.out.println("找房子");
    }
}
