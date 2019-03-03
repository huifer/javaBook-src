package com.huifer.aop.advisor;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * 描述:
 * 方法匹配器
 *
 * @author huifer
 * @date 2019-03-03
 */
public class MyMethodMatcher implements MethodMatcher {

    /**
     * 被监控接口的方法都是唯一的 使用这个方法，只根据名称判断！！！
     * {@link MyClassFilter}
     *
     * @param method 待判断的方法
     * @param aClass 目标类
     * @return boolean
     */
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        if (method.getName().equals("wc")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        return false;
    }
}
