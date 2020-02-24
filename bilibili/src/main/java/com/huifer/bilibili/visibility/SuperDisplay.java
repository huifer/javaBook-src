package com.huifer.bilibili.visibility;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public abstract class SuperDisplay {
    public Object display2(List<String> displayFiled) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            // 在显示字段中
            if (displayFiled.contains(name)) {
                declaredField.setAccessible(true);
                Object resultValue = declaredField.get(this);
                result.put(name, resultValue);
            }
        }
        return result;
    }
}
