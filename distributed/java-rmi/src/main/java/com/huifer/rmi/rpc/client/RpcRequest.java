package com.huifer.rmi.rpc.client;

import java.io.Serializable;

/**
 * <p>Title : RpcRequest </p>
 * <p>Description : 传输对象</p>
 *
 * @author huifer
 * @date 2019-06-11
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -993613829421519790L;
    /**
     * 存储类名
     */
    private String className;
    /**
     * 存储方法名
     */
    private String methodName;
    /**
     * 存储参数
     */
    private Object[] parameters;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
