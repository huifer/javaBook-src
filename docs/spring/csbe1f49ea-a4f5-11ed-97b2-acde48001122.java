package com.huifer.design.delegate.subordinate;

/**
 * <p>Title : QD </p>
 * <p>Description : 前端</p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class QD implements Dev {

    @Override
    public void work(String s) {
        System.out.println("前端工程师开始工作");
        System.out.println("前端任务 ： " + s);
    }
}
