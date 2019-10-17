package com.huifer.design.adapter.ChaTou;

/**
 * 描述:
 * 插头适配器
 * 不管什么插头都和标准配置
 *
 * @author huifer
 * @date 2019-03-13
 */
public class ChaAdapter implements QqCha {


    private Object cha;

    public ChaAdapter(Object cha) {
        this.cha = cha;
    }

    @Override
    public void method() {
        if (cha instanceof UsCha) {
            ((UsCha) cha).method();
        } else if (cha instanceof ChinaCha) {
            ((ChinaCha) cha).method();

        }

    }
}
