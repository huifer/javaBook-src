package com.huifer.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述:
 * 通知类
 *
 * @author huifer
 * @date 2019-02-24
 */
public class InformUtil implements InvocationHandler {

    /**
     * 被监控的对象，不能是具体实现类，要多具体行为进行监控
     */
    private BaseMothed obj;


    public InformUtil(BaseMothed mothed) {
        this.obj = mothed;
    }


    /***
     * 被监控行为执行时，拦截
     * @param proxy 代理对象
     * @param method 监控方法 主要业务
     * @param args method的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取返回结果
        Object result;

        // 判断行为
        String methodName = method.getName();
        if ("eat".equals(methodName)) {
            cc();
            result = method.invoke(this.obj, args);
        } else {
            cc();
            result = method.invoke(this.obj, args);
        }

        return result;
    }

    /***
     * 次要业务实现
     */
    public void cc() {
        System.out.println("监听一下下操作内容");
    }


}
