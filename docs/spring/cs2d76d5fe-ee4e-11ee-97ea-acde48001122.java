package com.huifer.aop.advisor;

import org.springframework.aop.ClassFilter;

/**
 * 描述:
 * 类过滤器
 *
 * @author huifer
 * @date 2019-03-03
 */
public class MyClassFilter implements ClassFilter {
    /***
     * 判断当前实现类是否是我们的织入所相关的类
     * 本次案例中 男性上厕所不需要带纸巾，女性上厕所需要带纸巾，
     * 那么BaseAopPointCut接口应该要对女性进行判断，完成此步骤后还需要一个方法匹配器 ，
     * 再次我们只要对上厕所匹配 吃饭不需要匹配
     *
     * @param aClass 当前被拦截的类
     * @return boolean
     */
    @Override
    public boolean matches(Class<?> aClass) {
        if (aClass == WoMan.class) {

            return true;
        }
        return false;
    }
}
