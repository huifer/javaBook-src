# Spring SingletonBeanRegistry
- Author: [HuiFer](https://github.com/huifer)
- 源码阅读仓库: [huifer-spring](https://github.com/huifer/spring-framework-read)

## SingletonBeanRegistry
- `org.springframework.beans.factory.config.SingletonBeanRegistry`
- `SingletonBeanRegistry`作用: 
    1. 注册方法
    2. 获取方法
- 默认实现:`org.springframework.beans.factory.support.DefaultSingletonBeanRegistry`
```java
public interface SingletonBeanRegistry {

    /**
     * 注册单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取一个单例对象
     */
    @Nullable
    Object getSingleton(String beanName);

    /**
     * 是否存在单例对象
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取单例 beanName
     */
    String[] getSingletonNames();

    /**
     *
     * 单例bean的数量
     */
    int getSingletonCount();

    /**
     */
    Object getSingletonMutex();

}
```
