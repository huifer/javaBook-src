package com.huifer.design.delegate.subordinate;

/**
 * <p>Title : YW </p>
 * <p>Description : 运维</p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class YW implements Dev {

    @Override
    public void work(String s) {
        System.out.println("运维工程师开始工作");
        System.out.println("运维任务 ： " + s);
    }
}
