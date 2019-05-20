package com.huifer.design.strategy;

import com.huifer.design.strategy.payport.PayType;

/**
 * <p>Title : Order </p>
 * <p>Description : 订单</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class Order {

    private String uid;
    private String oderId;
    private double amount;

    public Order(String uid, String oderId, double amount) {
        this.uid = uid;
        this.oderId = oderId;
        this.amount = amount;
    }

    public PayState pay(PayType payType) {
        return payType.get().pay(this.uid, this.amount);
    }
}
