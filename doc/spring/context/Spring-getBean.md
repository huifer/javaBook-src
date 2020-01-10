# Spring getBean
- Author: [HuiFer](https://github.com/huifer)
- 源码阅读仓库: [huifer-spring](https://github.com/huifer/spring-framework-read)

## 源码分析
- 基本测试用例
```java
public class ContextLoadSourceCode {
    public static void main(String[] args) {
        // 这是一个最基本的 spring 调用的例子
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ContextLoadSourceCode-beans.xml");
        Person bean = context.getBean(Person.class);
        System.out.println(bean.getName());
        System.out.println(bean.getApple().getName());
        bean.dis();
        System.out.println(bean.getAge());
    }
}
```
- `getBean`提供方`org.springframework.beans.factory.BeanFactory`
```java
public interface BeanFactory {

    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String name) throws BeansException;
    
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

    <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);

    <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType);

    boolean containsBean(String name);

    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;
   
    boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;
    
    @Nullable
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    String[] getAliases(String name);

}

```
### 方法概述
- `getBean`: 获取bean实例
- `containsBean`:判断bean是否存在
- `isSingleton`:判断bean的作用范围是否为singleton
- `isPrototype`:判断bean的作用范围是否为prototype
- `isTypeMatch`
- `getType`:获取bean的类型
- `getAliases`:获取bean的别名列表

### 入口
`org.springframework.context.support.AbstractApplicationContext.getBean(java.lang.Class<T>)`

```java
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        assertBeanFactoryActive();
        return getBeanFactory().getBean(requiredType);
    }

```
- `org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(java.lang.Class<T>)`
```java
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        //  getBean 
        return getBean(requiredType, (Object[]) null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> requiredType, @Nullable Object... args) throws BeansException {
        Assert.notNull(requiredType, "Required type must not be null");
        Object resolved = resolveBean(ResolvableType.forRawClass(requiredType), args, false);
        if (resolved == null) {
            throw new NoSuchBeanDefinitionException(requiredType);
        }
        return (T) resolved;
    }
```