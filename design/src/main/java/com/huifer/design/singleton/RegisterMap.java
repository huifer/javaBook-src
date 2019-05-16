package com.huifer.design.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title : RegisterMap </p>
 * <p>Description : 注册登记表</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class RegisterMap {

    private static Map<String, Object> register = new ConcurrentHashMap<>();

    private RegisterMap() {
    }

    public static synchronized RegisterMap getInstance(String name) {
        if (name == null) {
            name = RegisterMap.class.getName();
        }
        if (register.get(name) == null) {
            try {
                register.put(name, new RegisterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterMap) register.get(name);
    }


}
