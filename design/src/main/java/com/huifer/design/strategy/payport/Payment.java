package com.huifer.design.strategy.payport;

import com.huifer.design.strategy.PayState;

/**
 * <p>Title : Payment </p>
 * <p>Description : 支付</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public interface Payment {

    // 系统开始之初定义了2中支付方式，当增加一个新的支付方式时也需要维护这个接口 ， 不符合开闭原则
//    ZFB zfb = new ZFB();
//    VX VX = new VX();

    /**
     * 支付
     */
    PayState pay(String uid, double amount);
}
