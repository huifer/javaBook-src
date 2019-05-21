package com.huifer.design.delegate.subordinate;

/**
 * <p>Title : HD </p>
 * <p>Description : 后端</p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class HD implements Dev {

    @Override
    public void work(String s) {
        System.out.println("后端工程师开始工作");
        System.out.println("后端任务 ： " + s);
    }
}
