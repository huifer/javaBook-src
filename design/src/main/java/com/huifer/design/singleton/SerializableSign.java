package com.huifer.design.singleton;

import java.io.Serializable;

/**
 * <p>Title : SerializableSign </p>
 * <p>Description : 序列化与反序列化的单例模式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class SerializableSign implements Serializable {

    public final static SerializableSign Instance = new SerializableSign();

    private static final long serialVersionUID = 2263605502238537664L;

    private SerializableSign() {
    }


    public static SerializableSign getInstance() {
        return Instance;
    }


    private Object readResolve() {
        // 反序列化时替换实例
        return Instance;
    }


}
