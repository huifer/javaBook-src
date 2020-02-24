package com.huifer.bilibili.visibility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DemoEntity extends SuperDisplay implements ShowData {

    private String name;

    private Integer age;

    public static void main(String[] args) throws Exception {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setName("");
        demoEntity.setAge(11);

        List<String> s = new ArrayList<>();
        s.add("age");

        Object display = demoEntity.display(s);
        Object display2 = demoEntity.display2(s);
        System.out.println(display);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Object display(List<String> displayFiled) throws IllegalAccessException {
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
