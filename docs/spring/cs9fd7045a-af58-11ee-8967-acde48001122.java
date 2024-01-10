package com.huifer.design.strategy.payport;

/**
 * <p>Title : PayType </p>
 * <p>Description : 支付形式</p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public enum PayType {
    /**
     * 支付宝
     */
    ZFB(new ZFB()),
    /**
     * 微信
     */
    VX(new VX()),

    ;

    private Payment payment;


    PayType(Payment pay) {
        this.payment = pay;
    }

    /**
     * 获取支付形式
     *
     * @return
     */
    public Payment get() {
        return this.payment;
    }
}
