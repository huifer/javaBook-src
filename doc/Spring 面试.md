# Spring 面试
## IoC 的理解
- IoC 反转控制, 是一种设计思想。将创建对象的权限给 Spring , 实质上 IoC是一个 Map , 存放了各种对象, key ：beanName , value ： bean 对象
- 这里可能会问 IoC 初始化, 简单描述: 
    1. 读取 xml 文件
    2. 读取标签 , 解析标签属性
    3. 属性设置 
    4. 存入map集合中
    - 这里如果要求背诵笔者觉得难度很高. 或者说笔者背不出来, 这部分要追源码去细细的看. 面试的时候问这块应该是想问问有没有源码阅读习惯. 
    
    
## AOP 的理解
- 面向切面编程, 解耦. 比如将通用的东西抽象出来（事务,日志,权限）,对一个方法或者多个方法进行切点设置. 
- 通知方式
    1. 环绕通知
    1. 异常通知
    1. 前置通知
    1. 后置通知
- 实现方式
    1. 静态代理
        - 手写一个, 难
    1. 动态代理
        - cglib 使用
        - JDK 动态代理
    
## bean 作用域
- 单例 singleton
- 多例 prototype 
- request
- session 
- global-session


## bean 单例线程安全吗
- 不安全， 操作没有 static 修饰的都是不安全的
    - ThreadLocal 
    
## bean 生命周期
1. 找 xml 的配置信息
1. 反射创建
1. 设置属性
1. aware 接口调用
1. BeanPostProcessor， 前置方法 
1. InitializingBean
    1. init-method
1. BeanPostProcessor , 后置方法
1. 摧毁 destroy()
    1. destroy-method 
    
    
## Spring 设计模式
1. 工厂 ,beanFactory
1. 代理 , aop
1. 单例 , IoC bean 默认单例
1. 模板方法 , template 
1. 监听 , 事件

## Component 和 bean 的区别
- Component 作用类 ， bean 作用方法

## 事务
- 编程
- 声明

## 事务的隔离级别 事务的传播方式 
- 背书. 


