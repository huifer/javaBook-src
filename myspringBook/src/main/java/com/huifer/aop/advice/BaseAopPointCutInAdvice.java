package com.huifer.aop.advice;

/**
 * 描述:
 * aop 的切入点 ， 代理模式中的主要方法
 *
 * @author huifer
 * @date 2019-03-02
 */
public interface BaseAopPointCutInAdvice {
    /**
     * 吃饭
     */
    void eat();

    /**
     * 上厕所
     */
    void wc();
}
