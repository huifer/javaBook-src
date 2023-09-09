package com.huifer.utils.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
//import org.springframework.cglib.beans.BeanGenerator;
//import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtil {


    public static Object getTarget(Object source, Map<String, Object> addProperties) {
        // 获取原始数据的value
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(source);
        Map<String, Class> propertyMap = new HashMap<>();
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // 增加数据的值
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // 创建原始数据的实体对象
        DynamicBean dynamicBean = new DynamicBean(source.getClass(), propertyMap);
        // 老数据
        propertyMap.forEach((k, v) -> {
            try {
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(source, k));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 添加新数据
        addProperties.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }


    public static void main(String[] args) throws Exception {
        BaseBase entity = new BaseBase();
        Map<String, Object> addProperties = new HashMap() {{
            put("username", "username");
            put("pwd", "pwd");
            put("hcName", "hcName");
            put("hcName1", "hcName");
            put("hcName2", "hcName");
        }};
        Object target = getTarget(entity, addProperties);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(target);
        System.out.println(json);
        StuFat stuFat = new StuFat();
        BeanUtils.copyProperties(stuFat, target);
        System.out.println();
    }


    /**
     * 动态bean
     */
    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator = new BeanGenerator();
            if (null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

}