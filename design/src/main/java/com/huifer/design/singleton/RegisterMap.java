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

    private static final Object lock = new Object();
    private static Map<String, RegisterMap> register = new ConcurrentHashMap<>();

    private RegisterMap() {
    }

//    public static synchronized RegisterMap getInstance(String name)throws Exception{
    //        // 异常： 获取到的实例可能存在Null
//        if (register.containsKey(name)) {
//            return register.get(name);
//        } else {
//                Class<?> aClass = Class.forName(name);
//                Constructor<?> constructor = aClass.getDeclaredConstructor(null);
//                constructor.setAccessible(true);
//                return register.put(name, (RegisterMap) constructor.newInstance());
//        }
//    }

//
//    public static RegisterMap getInstance(String name) {
//        // 异常： 获取到的实例可能存在Null
//        if (register.get(name) == null) {
//            synchronized (RegisterMap.class) {
//                if (register.get(name) == null) {
//                    try {
//                        Class<?> aClass = Class.forName(name);
//                        Constructor<?> constructor = aClass.getDeclaredConstructor(null);
//                        constructor.setAccessible(true);
//                        return register.put(name, (RegisterMap) constructor.newInstance());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return null;
//                    }
//                }
//            }
//        }
//        return register.get(name);
//
//    }

    //     使用HashMap
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
        return register.get(name);
    }


}
