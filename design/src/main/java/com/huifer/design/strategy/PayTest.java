package com.huifer.design.strategy;

import com.huifer.design.strategy.payport.PayType;

/**
 * <p>Title : PayTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class PayTest {

    public static void main(String[] args) {
        // 订单创建
        Order order = new Order("张三的id", "1", 100);
        // 选择支付系统        // 支付宝 、 微信
        // 此时用户只需要选择一个支付方式即可
        System.out.println(order.pay(PayType.VX));
        System.out.println(order.pay(PayType.ZFB));

    }

}
