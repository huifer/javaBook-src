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

  

## 单例模式
### 饿汉式
- 不管是否使用都提前创建实例，避免了线程安全问题。
```java
public class Hungry {

    /**
     * 提前创建一个实例 且不能修改
     */
    private static final Hungry HUNGRY = new Hungry();

    /**
     * 私有化构造方法
     */
    private Hungry() {
    }

    /**
     * 返回实例
     */
    public static Hungry getInstance() {
        return HUNGRY;
    }

}
```
- 测试方法
```java
private static void hungryTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    Hungry instance = Hungry.getInstance();
                    System.out.println(System.currentTimeMillis() + " : " + instance);
                } catch (Exception e) {

                }
            }).start();
            latch.countDown();
        }

    }
```
查看同一时间是否出现两个对象

```
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
1557969460097 : com.huifer.design.singleton.Hungry@5f292f85
```







### 懒汉式
- 默认加载不进行实例化，在需要使用的时候才会进行实例化

    

    ```java
    public class Lazy1 {
    
        private static Lazy1 lazy1 = null;
    
        private Lazy1() {
        }
    
        public static Lazy1 getInstance() {
            if (lazy1 == null) {
                lazy1 = new Lazy1();
            }
            return lazy1;
        }
    
    }
    
    ```

    懒汉式的第一种测试

    ```java
        private static void lazyTest1()  {
            CountDownLatch latch = new CountDownLatch(count);
            for (int i = 0; i < count; i++) {
                new Thread(() -> {
    
                    try {
                        latch.await();
                        Lazy1 instance = Lazy1.getInstance();
                        System.out.println(System.currentTimeMillis() + " : " + instance);
                    } catch (Exception e) {
    
                    }
                }).start();
    
                latch.countDown();
            }
        }
    
    ```

    测试结果

    ```
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    1557969659291 : com.huifer.design.singleton.Lazy1@41562ac8// 这一行有问题
    1557969659291 : com.huifer.design.singleton.Lazy1@6a89c268
    ```

    多线程测试结果不符合预期，它出现了两个实例 ， 需要对其进行修改。解决方案：给getInstance 添加锁 （同步锁）

    ```java
    public class Lazy2 {
    
        private static Lazy2 lazy1 = null;
    
        private Lazy2() {
        }
    
        public static synchronized Lazy2 getInstance() {
            if (lazy1 == null) {
                lazy1 = new Lazy2();
            }
            return lazy1;
        }
    
    }
    ```

    测试结果

    ```
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    1557970444285 : com.huifer.design.singleton.Lazy2@3573fdb0
    ```

    然而使用同步锁 synchronized 带来了性能问题 速度变得慢了很多。使用静态内部类来提升性能 

    ```java
    public class Lazy3 {
    
       
        private Lazy3() {
    
        }
    
    
        public static final Lazy3 getInstance() {
            return lazyHolder.LAZY_3;
        }
    
    
        private static class lazyHolder {
    
            // 内部类会率先初始化
            private static final Lazy3 LAZY_3 = new Lazy3();
        }
    }
    ```

### 注册式

- 用一个Map来存储注册的实体，通过实体名称来获取

```java
public class RegisterMap {

    private static Map<String, Object> register = new HashMap<>();

    private RegisterMap() {
    }

    public static synchronized RegisterMap getInstance(String name) {
        if (name == null) {
            name = RegisterMap.class.getName();
        }
        if (register.get(name) == null) {
            try {
                register.put(name, new RegisterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterMap) register.get(name);
    }


}

```

- 线程安全测试

  ```java
  private static void registerTest() {
      CountDownLatch latch = new CountDownLatch(count);
      for (int i = 0; i < count; i++) {
          new Thread(() -> {
  
              try {
                  latch.await();
                  RegisterMap registerMap = RegisterMap.getInstance("registerMap");
  
                  System.out.println(System.currentTimeMillis() + " : " + registerMap);
              } catch (Exception e) {
  
              }
          }).start();
  
          latch.countDown();
      }
  
  }
  ```

- 测试结果

  ```
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  1557972891686 : com.huifer.design.singleton.RegisterMap@5ca02bd8
  ```

  



### 枚举式

```java
public enum EnumSing {
    INSTANCE;
    private Object instance;

    EnumSing() {
        instance = new Object();
    }

    public Object getInstance() {
        return instance;
    }

}

Object o = EnumSing.INSTANCE.getInstance();
```

### 序列化与反序列化

- 将实体类序列化到本地 ，再次读取这个类时保证唯一性

```
public class SerializableSign implements Serializable {

    public final static SerializableSign Instance = new SerializableSign();

    private static final long serialVersionUID = 2263605502238537664L;

    private SerializableSign() {
    }


    public static SerializableSign getInstance() {
        return Instance;
    }



}
```

- 测试方法

  ```java
  private static void serializableTest() {
      SerializableSign s1 = null;
      SerializableSign s2 = SerializableSign.getInstance();
      FileOutputStream fos = null;
      ObjectOutputStream oos = null;
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      try {
  
          // 测试序列化是否单例
          // 写入本地
          fos = new FileOutputStream("SerializableSign.obj");
          oos = new ObjectOutputStream(fos);
          oos.writeObject(s2);
          oos.flush();
          oos.close();
          // 从本地读取
          fis = new FileInputStream("SerializableSign.obj");
          ois = new ObjectInputStream(fis);
          s1 = (SerializableSign) ois.readObject();
          ois.close();
  
          System.out.println(s1);
          System.out.println(s2);
          System.out.println(s1 == s2);
      } catch (Exception e) {
  
      }
  
  
  }
  ```

  测试结果

  ```
  com.huifer.design.singleton.SerializableSign@7b23ec81
  com.huifer.design.singleton.SerializableSign@5cad8086
  false
  
  ```

  阅读[这篇文章](<https://www.ibm.com/developerworks/cn/java/j-5things1/index.html>),后知道了 在实体类种编写readResolve 方法来替换读取的实例对象

  ```java
  private Object readResolve() {
      // 反序列化时替换实例
      return Instance;
  }
  ```

  测试结果

  ```
  com.huifer.design.singleton.SerializableSign@5cad8086
  com.huifer.design.singleton.SerializableSign@5cad8086
  true
  ```

  通过 readResolve 方法修改后实例变成了唯一实例

### 总结

- 在整个系统运行过程中只有一个实例，有且只有一个。
- 保证单例的方案
  - 饿汉式
  - 懒汉式（线程安全问题的解决），spring 种的延迟加载
  - 注册式
  - 枚举式



## 原型模式

### 总结

- 在原始基础上创建一个新的，两者的实例不相同但是数据内容相同