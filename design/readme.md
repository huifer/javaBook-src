# 设计模式仓库
- 代理模式
- 适配模式
- 装饰模式
## 工厂模式
### 简单工厂模式
#### 基础内容
- 生活案例
  
  - 有一个集合工厂，在工厂里面生产 [ 蒙牛、 伊利、 特仑苏 ]等各大品牌的牛奶，用户向这个工厂下订单说：我要蒙牛
- 案例解读
  - 类：工厂，作用生产牛奶
  - 接口: 牛奶， 作用[蒙牛、 伊利、 特仑苏] 继承牛奶
  - main：从工厂中提出蒙牛的订单
  
  
#### 代码
- 牛奶类
```java
package com.huifer.design.factory;


public interface Milk {

    /**
     * 获取一个牛奶的名称
     */
    String getName();
}

public class MengNiu implements Milk {

    @Override
    public String getName() {
        return "蒙牛";
    }
}

public class YiLi implements Milk {

    @Override
    public String getName() {
        return "伊利";
    }
}

```

- 工厂类
```java
public class SimpleFactory {

    public Milk getMilk(String name) {
        if ("蒙牛".equals(name)) {
            return new MengNiu();
        } else if ("伊利".equals(name)) {
            return new YiLi();
        } else {
            return null;
        }
    }

}
```

- 测试类
```java
public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Milk mn = simpleFactory.getMilk("蒙牛");
        Milk yili = simpleFactory.getMilk("伊利");

        System.out.println(mn.getName());
        System.out.println(yili.getName());
    }

}

```
#### 小结
- 对用户而言不知道如何创建,创建过程对用户隐藏。小明只需要说我要蒙牛的牛奶，店家就给蒙牛的牛奶。

---
### 工厂方法模式
#### 基础内容
- 生活案例
  - 有两个工厂分别生产蒙牛、伊利，在工厂内部分别对配料进行调整
- 案例理解
  - 接口：工厂，作用：蒙牛、伊利的工厂都继承该工厂
  - 类： 蒙牛工厂类、伊利工厂类，作用：对牛奶进行生产
#### 代码
- 工厂接口以及工厂
```java
public interface MethodFactory {

    /**
     * 获取牛奶
     */
    Milk createMilk();

}

public class MengNiuFactory implements MethodFactory {

    @Override
    public Milk createMilk() {
        System.out.println("蒙牛材料清单 ： 牛奶 100 克");
        return new MengNiu();
    }

}

public class YiLiFactory implements MethodFactory {

    @Override
    public Milk createMilk() {
        System.out.println("蒙牛材料清单 ： 牛奶 200 克");
        return new YiLi();
    }

}

```
- 测试
```java
public class MethodFactoryTest {

    public static void main(String[] args) {
        MethodFactory factory = new MengNiuFactory();
        Milk milk = factory.createMilk();
        System.out.println(milk.getName());

        MethodFactory factory1 = new YiLiFactory();
        Milk milk1 = factory1.createMilk();
        System.out.println(milk.getName());
    }
}

```
####小结
- 工厂方法模式可以自定义各种类的创建过程，对类的创建足够自由

### 抽象工厂模式
#### 基础内容
- 生活案例
  - 我想造一个生产蒙牛的工厂，市场上直接可以购买整个生产机器配料。
  
- 案例理解
  - 市场上有很多牛奶工厂可供选择，你只需要选择你需要的。
  - 基类： 工厂基类 ， 作用：统一牛奶工厂
  - 类： 牛奶工厂，实现了工厂基类。 
#### 代码
- 工厂
```java
public abstract class AbstractFactory {

    public void hello() {
        System.out.println("hello factory");
    }

    /**
     * 蒙牛
     */
    public abstract Milk getMengNiu();

    /**
     * 伊利
     * @return
     */
    public abstract Milk getYiLi();

}


public class MilkFactory extends AbstractFactory {

    @Override
    public void hello() {
        super.hello();
    }

    @Override
    public Milk getMengNiu() {
        return new MengNiuFactory().createMilk();
    }

    @Override
    public Milk getYiLi() {
        return new YiLiFactory().createMilk();
    }

}

```
- 测试类
```java
public class AbsFactoryTest {

    public static void main(String[] args) {
        MilkFactory milkFactory = new MilkFactory();
        Milk mengNiu = milkFactory.getMengNiu();
        System.out.println(mengNiu.getName());

    }

}

```
#### 小结
- 用户只具有选择权，不再具有具体的参数的创建权。能够避免用户使用时根据参数而导致的异常

### 总结

- 创建工厂

  - 简单工厂：需要知道具体的创建参数
  - 工厂方法：需要知道有那些工厂
  - 抽象工厂：需要选择具体的工厂

  

