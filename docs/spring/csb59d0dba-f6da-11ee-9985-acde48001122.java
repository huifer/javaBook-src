package com.huifer.design.decorate.phone;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-13
 */
public class MatePhone implements HuaWei {

    // 装饰模式案例： 打电话之前有铃声 ， 铃声给装饰一个

    @Override
    public void call() {
        System.out.println("我在用mate系列手机打电话");
    }
}
