package com.huifer.aop.advisor;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-02
 */
public class WoMan implements BaseAopPointCut {
    @Override
    public void eat() {
        System.out.println("吃饭了");
    }

    @Override
    public void wc() {
        System.out.println("上厕所");
    }
}
