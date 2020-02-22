# Java 接口
## 接口介绍
- 关键字`interface`
- 接口通过`implements`进行实现

## 简单使用
```java
interface IHello {
    void hello();
}

class HelloImpl implements IHello {
    public void hello() {
        System.out.println("hello");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        hello.hello();
    }
}
```

## 接口进阶
- 应用案例如下
1. 假设现有需求如下,对ppt、excel进行操作抽象应该如何抽象,为了方便这里的操作为创建
1. 对redis,mysql操作进行抽象,操作:导入
1. 根据不同的参数进行对应操作的执行
    - 输入操作code: 分别对应不同的操作
        1. 输入1: 操作ppt
        1. 输入2: 操作excel
        1. 输入3: 操作redis
        1. 输入4: 操作mysql



### 第一版本
- 按照需求进行接口封装`DBOperation`和`OfficeOperation`
```java
package com.huifer.bilibili.inteface;

/**
 * 数据库操作
 */
public interface DBOperation {
    void importData();
}

```


```java
package com.huifer.bilibili.inteface;

/**
 * office 操作
 */
public interface OfficeOperation {
    /**
     * 创建文件
     */
    void create();
}

```
