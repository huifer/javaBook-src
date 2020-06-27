package com.huifer.design.singleton.nw;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private static final SerializableSingleton singleton = new SerializableSingleton();

    private SerializableSingleton() {
    }


    public static SerializableSingleton getInstance() {
        return singleton;
    }

    private Object readResolve() {
        return singleton;
    }
}
