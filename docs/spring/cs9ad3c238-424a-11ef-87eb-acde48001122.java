package com.huifer.jdk.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * {@link java.beans.BeanInfo}
 */
public class BeanInfoUseDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        // 基本要素
        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
//            System.out.println(propertyDescriptor);

//            PropertyDescriptor 属性编辑器 -> PropertyEditor
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyDescriptorName = propertyDescriptor.getName();
            if ("age".equals(propertyDescriptorName)) {
                // 为 age 属性增加 PropertyEditor
                // String to integer
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
            }
        });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }

    }
}
