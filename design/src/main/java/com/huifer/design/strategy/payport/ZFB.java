package com.huifer.design.strategy.payport;

import com.huifer.design.strategy.PayState;

/**
 * <p>Title : ZFB </p>
 * <p>Description : 支付宝</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class ZFB implements Payment {

    @Override
    public PayState pay(String uid, double amount) {
        double random = Math.random();
        System.out.println("欢迎支付宝支付");

        if (random > 0.5) {
            return new PayState(200, "支付宝，支付成功", "ok");
        } else {
            return new PayState(404, "支付宝，支付失败", "bad");

        }
    }
}
