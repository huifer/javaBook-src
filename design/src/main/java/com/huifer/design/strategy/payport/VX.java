package com.huifer.design.strategy.payport;

import com.huifer.design.strategy.PayState;

/**
 * <p>Title : VX </p>
 * <p>Description : 微信</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class VX implements Payment {

    @Override
    public PayState pay(String uid, double amount) {
        double random = Math.random();
        System.out.println("欢迎微信支付");
        if (random > 0.5) {
            return new PayState(200, "微信，支付成功", "ok");
        } else {
            return new PayState(404, "微信，支付失败", "bad");

        }
    }
}
