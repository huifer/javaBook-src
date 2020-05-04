# 传统IoC容器实现

- Java Beans 作为容器
- 特性
  1. 依赖查找
  2. 生命周期管理
  3. 配置元信息
  4. 事件
  5. 持久化
  6. 资源管理
  7. 自定义





## JavaBeans 

- 一个POJO 对象在JavaBeans中存在两种方法
  1. 可写方法(Setter,Writable)
  2. 可读方法(Getter,Readable)





```java
public class BeanInfoUseDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

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
```



## Spring IoC

- 根据 Bean 名称查找
  - 实时查找
  - 延时查找
- 根据 Bean 类型查找
  - 单个 Bean 对象
  - 集合 Bean 对象
- 根据 Bean 名称 + Bean 类型查找
- 根据注解查找
  - 单个 Bean 对象
  - 集合 Bean 对象