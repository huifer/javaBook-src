package com.huifer.design.singleton;

/**
 * <p>Title : EnumSing </p>
 * <p>Description : 枚举式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public enum EnumSing {
    INSTANCE;
    private Object instance;

    EnumSing() {
        instance = new Object();
    }

    public Object getInstance() {
        return instance;
    }

}
