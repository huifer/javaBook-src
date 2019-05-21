package com.huifer.design.decorate.phone;

/**
 * 描述:
 * 装饰华为,这个是装饰类
 *
 * @author huifer
 * @date 2019-03-13
 */
public class HuaWeiDec implements HuaWei {
    /**
     * 被装饰类
     */
    private HuaWei huaWei;

    public HuaWeiDec(HuaWei matePhone) {
        this.huaWei = matePhone;
    }

    @Override
    public void call() {
        System.out.println("来点铃声开始了");
        huaWei.call();

    }
}
