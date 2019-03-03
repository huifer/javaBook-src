package com.huifer.aop.advisor;

import lombok.Setter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
@Setter
public class MyPointCut implements Pointcut {

    /**
     * 类型过滤器
     */
    private ClassFilter classFilter;
    /**
     * 方法过滤器
     */
    private MethodMatcher matcher;


    /**
     * @return 返回类过滤器
     */
    @Override
    public ClassFilter getClassFilter() {
        return this.classFilter;
    }


    /**
     * @return 返回方法过滤器
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this.matcher;
    }
}
