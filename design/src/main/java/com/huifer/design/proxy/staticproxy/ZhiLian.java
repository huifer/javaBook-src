package com.huifer.design.proxy.staticproxy;

/**
 * <p>Title : ZhiLian </p>
 * <p>Description : 智联</p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class ZhiLian {

    private Person p;

    public ZhiLian(Person person) {
        this.p = person;
    }

    public void findWork() {
        // 智联帮你找工作
        System.out.println("智联正在帮你寻找工作");
        this.p.findWork();
        System.out.println("帮你找到工作了");
    }

}
