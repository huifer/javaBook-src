package com.huifer.aop.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * 描述:
 * 自定义顾问
 *
 * @author huifer
 * @date 2019-03-03
 */
public class MyPointCutAdvisor implements PointcutAdvisor {
    /**
     * 次要业务主要业务 + 执行顺序
     * 本次案例 上厕所 + 带纸巾
     */
    private Advice advice;
    /**
     * 拦截对象+ 主要业务
     * 本次案例 女人 + 上厕所
     */
    private Pointcut pointcut;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
