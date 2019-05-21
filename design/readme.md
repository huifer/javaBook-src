[TOC]

# 设计模式仓库

## 代理模式

### 静态代理

- 代理对象和目标对象实现相同接口
- 优点
  - 在不改变目标对象的前提下拓展目标对象
  - 保护具体业务逻辑，不对外暴露
- 缺点
  - 额外代理类数量多

#### 代码

- 以找工作为例，在智联上找工作

    ```java
    public class Person {

        public void findWork() {
            System.out.println("找工作");
        }

    }

    public class ZhiLian {

        private Person p;

        public ZhiLian(Person person) {
            this.p = person;
        }

        public void findWork() {
            // 智联帮你找工作
            System.out.println("智联正在帮你寻找工作");
            this.p.findWork();
            System.out.println("帮你找到工作了");
        }

    }
    
    public class StataicProxyTest {
    
        public static void main(String[] args) {
            Person p = new Person();
            ZhiLian zhiLian = new ZhiLian(p);
            zhiLian.findWork();
        }
    
    }
    ```
    
    - 运行结果
    
      ```
      智联正在帮你寻找工作
      找工作
      帮你找到工作了
      ```
    
    - 思考：当找到了工作以后，找房子，也将成为一个问题 需要去解决，你可能会选择一个租房公司来帮你找房子...... 难么就会需要另一个类 租房类。当需求越来越多，这些代理类也会越来越多


​      





### 动态代理

#### JDK 动态代理

- 优点
  - 解决静态代理中冗余代理的问题
- 缺点
  - JDK动态代理基于接口进行开发，必须要有一个接口



- 代码

  ```java
  public class PersonJdk {
  
      public void findWork() {
          System.out.println("找工作");
      }
  
  }
  public class ZhiLianJdk {
  
      /**
       * 被代理对象的临时保存结点
       */
      private PersonJdk target;
  
      public Object getInstance(PersonJdk personJdk) {
          this.target = personJdk;
          Class clazz;
  
          clazz = personJdk.getClass();
  
          // 重构一个新的对象
          return  Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                  new InvocationHandler() {
                      @Override
                      public Object invoke(Object proxy, Method method, Object[] args)
                              throws Throwable {
                          System.out.println("jdk 代理的智联");
  
                          Object invoke = method.invoke(target, args);
                          System.out.println("工作找到了");
                          return null;
                      }
                  });
  
      }
  }
  
  ```

  - 测试

    ```java
    public class JdkProxy {
    
        public static void main(String[] args) {
            PersonJdk pjd = new PersonJdk();
            Object obj = new ZhiLianJdk().getInstance(pjd);
    
            System.out.println(obj);
    
        }
    
    }
    ```

    测试结果如下

    ```
    jdk 代理的智联
    工作找到了
    null
    ```

  - 思考：我拿到的是一个obj 而不是PersonJdk 这个类 真正执行的方法应该时PersonJdk.findwok 。对测试类进行修改

    ```java
    public class JdkProxy {
    
        public static void main(String[] args) {
            PersonJdk pjd = new PersonJdk();
            Object obj = new ZhiLianJdk().getInstance(pjd);
    
            PersonJdk p = (PersonJdk) obj;
            p.findWork();
    
    //        System.out.println(obj);
    
        }
    
    }
    ```

  - 此时会抛出如下异常

    ```
    Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.huifer.design.proxy.jdk.PersonJdk
    	at com.huifer.design.proxy.jdk.JdkProxyTest.main(JdkProxy.java:16)
    ```

  - 为什么出现了这个问题？

    - jdk代理模式的实现 被代理对象必须实现一个接口
    - ![1558057278605](assets/1558057278605.png)

  - 解决方案

    - 增加一个接口

      ```java
      public interface ZhiYuan {
      
          /**
           * 找工作
           */
          void findWork();
      
      }
      ```

      ```java
      public class PersonJdk implements ZhiYuan {
      
          @Override
          public void findWork() {
              System.out.println("找工作");
          }
      
      }
      ```

      ```java
      public class ZhiLianJdk {
      
          /**
           * 被代理对象的临时保存结点 从personjdk 提升到一个接口
           */
          private ZhiYuan target;
      
          public Object getInstance(ZhiYuan personJdk) {
              this.target = personJdk;
              Class clazz;
      
              clazz = personJdk.getClass();
      
              // 重构一个新的对象
              return  Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                      new InvocationHandler() {
                          @Override
                          public Object invoke(Object proxy, Method method, Object[] args)
                                  throws Throwable {
                              System.out.println("jdk 代理的智联");
      
                              Object invoke = method.invoke(target, args);
                              System.out.println("工作找到了");
                              return invoke;
                          }
                      });
      
          }
      }
      ```

      ```java
      public class JdkProxy {
      
          public static void main(String[] args) {
              PersonJdk pjd = new PersonJdk();
              Object obj = new ZhiLianJdk().getInstance(pjd);
              // 注意 JDK 代理实现的是接口 并不是实现了接口的类 (PersonJdk)
              ZhiYuan p = (ZhiYuan) obj;
              p.findWork();
      
      //        System.out.println(obj);
      
          }
      
      }
      ```

      运行结果

      ```
      jdk 代理的智联
      找工作
      工作找到了
      ```

  - 新的需求：职员想要找个房子居住。解：这个就只需要完成一个新的代理类即可

    ```java
    public interface ZhiYuan {
    
        /**
         * 找工作
         */
        void findWork();
    
        /**
         * 找房子
         */
        void findHouse();
    
    }
    ```

    ```java
    public class Jdk58 {
    
        private ZhiYuan target;
    
    
        public Object getInstance(ZhiYuan z) {
            this.target = z;
    
            Class clazz = null;
            clazz = z.getClass();
            Object o = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args)
                                throws Throwable {
                            System.out.println("58同城为你服务");
    
                            Object invoke = method.invoke(target, args);
    
                            return invoke;
                        }
    
                    });
            return o;
    
        }
    
    }
    ```

    ```java
    public class JdkProxy {
    
        public static void main(String[] args) {
            PersonJdk pjd = new PersonJdk();
            Object obj = new ZhiLianJdk().getInstance(pjd);
            // 注意 JDK 代理实现的是接口 并不是实现了接口的类 (PersonJdk)
            ZhiYuan p = (ZhiYuan) obj;
            p.findWork();
    
    //        System.out.println(obj);
            System.out.println("===========");
            ZhiYuan instance = (ZhiYuan) new Jdk58().getInstance(pjd);
            instance.findHouse();
    
    
        }
    
    }
    ```

    运行结果

    ```
    jdk 代理的智联
    找工作
    工作找到了
    ===========
    58同城为你服务
    找房子
    ```





#### cglib动态代理

- 优点

  - 没有接口也能实现动态代理，采用字节码增强。

- 代码

  ```java
  public class CGPerson {
      public void findWork() {
          System.out.println("找工作");
      }
  }
  
  public class CGLIBZhiLian {
  
      public Object getInstance(Class<?> clazz) throws Exception {
          Enhancer enhancer = new Enhancer();
          enhancer.setSuperclass(clazz);
  
          enhancer.setCallback(new MethodInterceptor() {
              @Override
              public Object intercept(Object o, Method method, Object[] args,
                      MethodProxy methodProxy) throws Throwable {
                  System.out.println("CGLIB 代理智联");
                  Object o1 = methodProxy.invokeSuper(o, args);
                  return o1;
              }
          });
  
          return enhancer.create();
  
      }
  }
  
  
  ```

  ```java
  public class CglibProxyTest {
  
      public static void main(String[] args) throws Exception {
  
  
          CGPerson instance = (CGPerson) new CGLIBZhiLian().getInstance(CGPerson.class);
          instance.findWork();
  
      }
  
  }
  ```

  运行结果

  ```
  CGLIB 代理智联
  找工作
  ```

  



### 深究

#### 运行流程(字节码增强)

1. 获取被代理对象的引用，通过jdk Proxy 创建新的类还需要，被代理对象的所有接口
2. jdk Proxy 创建新的类 ， 同时实现所有接口
3. 在 InvocationHandler 添加自己的处理逻辑
4. 编译成class文件，在由JVM 进行调用

#### 代理后的编码

- 代理对象并不是我们编写好的具体一个java文件，它是通过字节码增强技术创建，研究其代码我们需要将在内存中的类保存的本地。以JDK代理为例进行描述。

- 这是一个经过动态代理的类p

    ```java
    public class JdkProxyTest {

        public static void main(String[] args) {
            PersonJdk pjd = new PersonJdk();
            Object obj = new ZhiLianJdk().getInstance(pjd);
            ZhiYuan p = (ZhiYuan) obj;
            p.findWork();
            System.out.println(p.getClass());
        }
    }
    ```
    
    运行结果`class com.sun.proxy.$Proxy0`

- 将该类持久化到本地磁盘。核心代码如下

  ```java
   public static void main(String[] args) {
          PersonJdk pjd = new PersonJdk();
          Object obj = new ZhiLianJdk().getInstance(pjd);
          ZhiYuan p = (ZhiYuan) obj;
          p.findWork();
          System.out.println(p.getClass());
  
          try {
   			// 需要提前知道代理类的名字 
              byte[] proxyClass = ProxyGenerator
                      .generateProxyClass("$Proxy0", new Class[]{Person.class});
              FileOutputStream fos = new FileOutputStream(
                      "E:\\mck\\javaBook-src\\design\\src\\main\\resources\\proxy.class");
  
              fos.write(proxyClass);
              fos.close();
  
          } catch (Exception e) {
              e.printStackTrace();
  
          }
      }
  ```

- 代理类查看

  ```java
  //
  // Source code recreated from a .class file by IntelliJ IDEA
  // (powered by Fernflower decompiler)
  //
  
  import com.huifer.design.proxy.staticproxy.Person;
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  import java.lang.reflect.UndeclaredThrowableException;
  
  public final class $Proxy0 extends Proxy implements Person {
      private static Method m1;
      private static Method m8;
      private static Method m2;
      private static Method m3;
      private static Method m6;
      private static Method m5;
      private static Method m7;
      private static Method m9;
      private static Method m0;
      private static Method m4;
  
      public $Proxy0(InvocationHandler var1) throws  {
          super(var1);
      }
  
      public final boolean equals(Object var1) throws  {
          try {
              return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
          } catch (RuntimeException | Error var3) {
              throw var3;
          } catch (Throwable var4) {
              throw new UndeclaredThrowableException(var4);
          }
      }
  
      public final void notify() throws  {
          try {
              super.h.invoke(this, m8, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final String toString() throws  {
          try {
              return (String)super.h.invoke(this, m2, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final void findWork() throws  {
          try {
              super.h.invoke(this, m3, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final void wait(long var1) throws InterruptedException {
          try {
              super.h.invoke(this, m6, new Object[]{var1});
          } catch (RuntimeException | InterruptedException | Error var4) {
              throw var4;
          } catch (Throwable var5) {
              throw new UndeclaredThrowableException(var5);
          }
      }
  
      public final void wait(long var1, int var3) throws InterruptedException {
          try {
              super.h.invoke(this, m5, new Object[]{var1, var3});
          } catch (RuntimeException | InterruptedException | Error var5) {
              throw var5;
          } catch (Throwable var6) {
              throw new UndeclaredThrowableException(var6);
          }
      }
  
      public final Class getClass() throws  {
          try {
              return (Class)super.h.invoke(this, m7, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final void notifyAll() throws  {
          try {
              super.h.invoke(this, m9, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final int hashCode() throws  {
          try {
              return (Integer)super.h.invoke(this, m0, (Object[])null);
          } catch (RuntimeException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      public final void wait() throws InterruptedException {
          try {
              super.h.invoke(this, m4, (Object[])null);
          } catch (RuntimeException | InterruptedException | Error var2) {
              throw var2;
          } catch (Throwable var3) {
              throw new UndeclaredThrowableException(var3);
          }
      }
  
      static {
          try {
              m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
              m8 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("notify");
              m2 = Class.forName("java.lang.Object").getMethod("toString");
              m3 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("findWork");
              m6 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("wait", Long.TYPE);
              m5 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("wait", Long.TYPE, Integer.TYPE);
              m7 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("getClass");
              m9 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("notifyAll");
              m0 = Class.forName("java.lang.Object").getMethod("hashCode");
              m4 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("wait");
          } catch (NoSuchMethodException var2) {
              throw new NoSuchMethodError(var2.getMessage());
          } catch (ClassNotFoundException var3) {
              throw new NoClassDefFoundError(var3.getMessage());
          }
      }
  }
  ```

  1. final class 不可被修改的类
  2. implements Person 继承person 继承一个接口
  3. m1,m2,m3... 就是接口方法的实现

  我们实现的方法是findWork`m3 = Class.forName("com.huifer.design.proxy.staticproxy.Person").getMethod("findWork");`

  ``` java
  public final void findWork() throws  {
      try {
          super.h.invoke(this, m3, (Object[])null);
      } catch (RuntimeException | Error var2) {
          throw var2;
      } catch (Throwable var3) {
          throw new UndeclaredThrowableException(var3);
      }
  }
  ```

  - super.h.invoke()是什么？

    `public final class $Proxy0 extends Proxy implements Person {}`

    h 从Proxy中继承过来

    ```java
    public class Proxy implements java.io.Serializable {
    // ...省略其他内容
        protected InvocationHandler h;
       // ... 省略其他内容
        }
    ```

    - InvocationHandler 是我们自己写的一个方法，代码如下，重写的InvocationHandler就是 Proxy 中的 h

      ```java
      public class ZhiLianJdk {
      
          /**
           * 被代理对象的临时保存结点
           */
          private ZhiYuan target;
      
          public Object getInstance(ZhiYuan personJdk) {
              this.target = personJdk;
              Class clazz;
      
              clazz = personJdk.getClass();
      
              // 重构一个新的对象
              return  Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                      new InvocationHandler() {
                          @Override
                          public Object invoke(Object proxy, Method method, Object[] args)
                                  throws Throwable {
                              System.out.println("jdk 代理的智联");
      
                              Object invoke = method.invoke(target, args);
                              System.out.println("工作找到了");
                              return invoke;
                          }
                      });
      
          }
      }
      ```

  - super.h.invoke()就是 Proxy.newProxyInstance 中的InvocationHandler.invoke











### 总结

- 静态代理：必须已知所有内容，运行前就知道。
- 动态代理：对代理内容未知，运行时才知道。





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







###  <a name="lanhan">懒汉式</a>  
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

    懒汉式

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
    
    在这个基础上依然存在一个问题 ， 强制访问私有构造方法来创建实例。 当强制生产两个实例时需要抛出异常
    
    ```java
    public class Lazy4 {
    
    
        private static boolean initialized = false;
    
        private Lazy4() {
            synchronized (Lazy4.class) {
                if (initialized == false) {
                    initialized = !initialized;
                } else {
                    throw new RuntimeException("单例初始化异常 ， 私有构造方法被强制使用");
                }
            }
        }
    
        public static final Lazy4 getInstance() {
            return lazyHolder.LAZY_3;
        }
    
    
        private static class lazyHolder {
    
            private static final Lazy4 LAZY_3 = new Lazy4();
        }
    }
    ```
    
    ```java
    private static void lazy4Test() {
        try {
    
            Class<Lazy4> lazy4Class = Lazy4.class;
            // 获取私有构造方法 com.huifer.design.singleton.Lazy4.Lazy4
            Constructor<Lazy4> constructor = lazy4Class.getDeclaredConstructor(null);
            // 强制生产
            constructor.setAccessible(true);
            // 构造2次
            Lazy4 lazy4_1 = constructor.newInstance();
            Lazy4 lazy4_2 = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
    
        }
    
    }
    ```
    
    测试结果存在异常 ，这个异常符合我们的预设
    
    ```
    java.lang.reflect.InvocationTargetException
    	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
    	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
    	at com.huifer.design.singleton.Test.lazy4Test(Test.java:202)
    	at com.huifer.design.singleton.Test.main(Test.java:28)
    Caused by: java.lang.RuntimeException: 单例初始化异常 ， 私有构造方法被强制使用
    	at com.huifer.design.singleton.Lazy4.<init>(Lazy4.java:20)
    	... 6 more
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
    - 在类加载的时候就初始化，对象是单例的
    - 优点：没有锁，执行效率高，线程绝对安全
    - 缺点：始终占据一部分内存空间
  - 懒汉式（线程安全问题的解决），spring 种的延迟加载
    - 默认不实例化，在使用过程中产生实例，通过方法调用来创建。存在线程安全问题，具体操作请看上述-[懒汉式](#lanhan) 
    - 静态内部类的性能最优
    - 优点：内存空间利用高
    
  - 注册式
  - 枚举式





## 原型模式

> 原型模式是创建型模式的一种，其特点在于通过“复制”一个已经存在的实例来返回新的实例,而不是新建实例。被复制的实例就是我们所称的“原型”，这个原型是可定制的。
> 原型模式多用于创建复杂的或者耗时的实例，因为这种情况下，复制一个已经存在的实例使程序运行更高效；或者创建值相等，只是命名不一样的同类数据。

### JDK 实现

- JDK 官方提供了一种原型模式 Cloneable

```java
public class Prototype implements Cloneable {

    public String name;
    public Tag tag;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Tag {

    public String f;

    public Tag(String f) {
        this.f = f;
    }
}

```

- 测试用例

  ```java
  public class PrototypeTest {
  
      public static void main(String[] args) throws CloneNotSupportedException {
          Prototype prototype = new Prototype();
          prototype.name = "张三";
          prototype.tag = new Tag("123");
  
          Prototype clone = (Prototype) prototype.clone();
  
          clone.tag.f = "asasas";
          System.out.println(clone.tag.f);
          System.out.println(prototype.tag.f);
  
          System.out.println(clone.tag);
          System.out.println(prototype.tag);
  
      }
  
  }
  ```

- 运行结果

  ```
  asasas
  asasas
  com.huifer.design.prototype.Tag@330bedb4
  com.huifer.design.prototype.Tag@330bedb4
  ```

- 在这个测试用例种修改了clone 后的属性原来的实例属性也被同时修改，这是一个需要解决的问题。**此处出现了地址引用同一个地址，这是一种浅拷贝**

  - 使用**序列化来辅助深拷贝**
  - **自定义的属性也需要序列化**！！！

  ```java
  public Object deepClone() {
      try {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ObjectOutputStream oos = new ObjectOutputStream(baos);
          oos.writeObject(this);
  
          ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
          ObjectInputStream ois = new ObjectInputStream(bis);
  
          Prototype copyObject = (Prototype) ois.readObject();
          return copyObject;
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
  }
  ```

  测试用例

  ```java
  public class PrototypeTest {
  
      public static void main(String[] args) throws Exception {
          Prototype prototype = new Prototype();
          prototype.name = "张三";
          prototype.tag = new Tag("123");
  
          Prototype clone = (Prototype) prototype.clone();
  
          clone.tag.f = "asasas";
          System.out.println(clone.tag.f);
          System.out.println(prototype.tag.f);
  
          System.out.println(clone.tag);
          System.out.println(prototype.tag);
  
          System.out.println(prototype.name == clone.name);
          System.out.println(prototype.tag == clone.tag);
      }
  
  }
  ```

  运行结果

  ```
  asasas
  123
  com.huifer.design.prototype.Tag@2f4d3709
  com.huifer.design.prototype.Tag@5e2de80c
  false
  false
  ```

  - 从结果上看可以说这两个实例是独立的。



### 总结

- 在原始基础上创建一个新的，两者的实例不相同但是数据内容相同





## 策略模式

### 定义

> 策略模式作为一种软件设计模式，指对象有某个行为，但是在不同的场景中，该行为有不同的实现算法。比如每个人都要“交个人所得税”，但是“在美国交个人所得税”和“在中国交个人所得税”就有不同的算税方法。

常用场景

- 数值比较
- 支付
- 一个算法
- 一个流程

### 代码讲解

#### 比较器

- 人有高矮胖瘦，想要对次进行排序那么就需要比较器，java 中比较器使用 Collections.sort(java.util.Comparator)  或者 java.lang.Comparable

- Comparable

  ```java
  public class People implements Comparable<People> {
  
      public double height;
      public double weight;
      public People() {
      }
  
      public People(double height, double weight) {
          this.height = height;
          this.weight = weight;
      }
  
      @Override
      public int compareTo(People o) {
          return (int) (this.height - o.height);
      }
  
      @Override
      public String toString() {
          final StringBuilder sb = new StringBuilder("{");
          sb.append("\"height\":")
                  .append(height);
          sb.append(",\"weight\":")
                  .append(weight);
          sb.append('}');
          return sb.toString();
      }
  }
  ```

  ```java
  public class MyComparator {
  
      public static void main(String[] args) {
          // 人有高矮胖瘦
          List plist = new ArrayList<>();
          plist.add(new People(3, 3));
          plist.add(new People(1, 1));
          plist.add(new People(4, 4));
          plist.add(new People(2, 2));
  
          Collections.sort(plist);
          System.out.println(plist);
  
      }
  }
  ```

  - 问题

    1. 排序规则只有在实体类中存在，换一个排序需要重新修改实体类。
    2. 该方案必须实现Comparable 如果没有实现怎么办？
    3. 额外增加一个排序规则怎么办？

  - 综上所述使用Comparable 的耦合度比较高，不易扩展

    

- Comparator  

  - 使用 java.util.Comparator 

  ```java
  public class People {
  
      public double height;
      public double weight;
  
      public People(double height, double weight) {
          this.height = height;
          this.weight = weight;
      }
  }
  ```

  ```java
  public class MyComparatorTest {
  
      public static void main(String[] args) {
          // 人有高矮胖瘦
          peopleComparable();
  
          List<People> plist = new ArrayList<>();
          plist.add(new People(3, 3));
          plist.add(new People(1, 1));
          plist.add(new People(4, 4));
          plist.add(new People(2, 2));
  		// 按照weight 排序
          plist.sort(sortWeight());
  		// 按照height 排序
          plist.sort(sortHeight());
  
      }
  
      private static Comparator<People> sortWeight() {
          return new Comparator<People>() {
              @Override
              public int compare(People o1, People o2) {
                  return (int) (o1.weight - o2.weight);
              }
          };
      }
  
      private static Comparator<People> sortHeight() {
          return new Comparator<People>() {
              @Override
              public int compare(People o1, People o2) {
                  return (int) (o1.height - o2.height);
              }
          };
      }
  
  }
  ```

  - 将排序规则提出，作为一个新的方法进行解耦。数据与操作分离







#### 支付

- 以支付场景为例，支付时可以选择多个支付形式。

  ```java
  // 订单
  public class Order {
  
      private String uid;
      private String oderId;
      private double amount;
  
      public PayState pay(Payment payment) {
          return payment.pay(this.uid, this.amount);
      }
  
      public Order(String uid, String oderId, double amount) {
          this.uid = uid;
          this.oderId = oderId;
          this.amount = amount;
      }
  }
  
  // 支付信息
  public class PayState {
  
      private int code;
      private Object data;
      private String msg;
  }
  
  // 支付方式
  public interface Payment {
  
      ZFB zfb = new ZFB();
      VX VX = new VX();
  
      /**
       * 支付
       */
      PayState pay(String uid, double amount);
  }
  // 微信支付
  public class VX implements Payment {
  
      @Override
      public PayState pay(String uid, double amount) {
          double random = Math.random();
          System.out.println("欢迎微信支付");
          if (random > 0.5) {
              return new PayState(200, "微信，支付成功", "ok");
          } else {
              return new PayState(404, "微信，支付失败", "bad");
  
          }
      }
  }
  // 支付宝支付
  public class ZFB implements Payment {
  
      @Override
      public PayState pay(String uid, double amount) {
          double random = Math.random();
          System.out.println("欢迎支付宝支付");
  
          if (random > 0.5) {
              return new PayState(200, "支付宝，支付成功", "ok");
          } else {
              return new PayState(404, "支付宝，支付失败", "bad");
  
          }
      }
  }
  // 测试类
  public class PayTest {
  
      public static void main(String[] args) {
          // 订单创建
          Order order = new Order("张三的id", "1", 100);
          // 选择支付系统        // 支付宝 、 微信
          // 此时用户只需要选择一个支付方式即可
          System.out.println(order.pay(Payment.VX));
          System.out.println(order.pay(Payment.zfb));
  
      }
  
  }
  
  ```

- 这样可以简单实现一个支付形式，但是每当开发者增加一个支付方式 ，在Payment 中也需要同步增加。违背**开闭原则**。尽可能减少接口的修改，将支付方式使用枚举进行保存。

  ```java
  public enum PayType {
      /**
       * 支付宝
       */
      ZFB(new ZFB()),
      /**
       * 微信
       */
      VX(new VX()),
  
      ;
  
      private Payment payment;
  
  
      PayType(Payment pay) {
          this.payment = pay;
      }
  
      /**
       * 获取支付形式
       * @return
       */
      public Payment get() {
          return this.payment;
      }
  }
  ```

  ```java
  public class Order {
  
      private String uid;
      private String oderId;
      private double amount;
  
      public Order(String uid, String oderId, double amount) {
          this.uid = uid;
          this.oderId = oderId;
          this.amount = amount;
      }
  
      public PayState pay(PayType payType) {
          // 从原始调用接口改为从枚举中获取在执行
          return payType.get().pay(this.uid, this.amount);
      }
  }
  ```

- 后续拓展新的开发内容维护  payType即可

### 总结

1. 执行过程固定，执行过程不可见，仅提供选择
2. 可以省略一些switch ， if else 





## 模板模式（模板方法）

### 定义

> 模板方法模式定义了一个算法的步骤，并允许子类别为一个或多个步骤提供其实践方式。让子类别在不改变算法架构的情况下，重新定义算法中的某些步骤。



### 代码讲解

- 我们经常会使用JDBC来进行数据库的操作，在这个JDBC操作数据库的过程中有很多内容是重复进行的，而我们进行操作的更多的是SQL部分，我们将JDBC操作定义如下几个部分

  1. 创建datasource
  2. 从datasource中获取连接Connection
  3. 使用conn来进行预编译PreparedStatement
  4. 执行查询获得结果 ResultSet
  5. 解析结果
  6. 关闭资源

  - 我们每次进行操作的有 第4、5步

```java
public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Object> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));

        }
        return result;

    }

    public List<Object> executeQuery(String sql, RowMapper<?> rowMapper, Object[] value) {
        try {

            // 1.获取连接
            Connection conn = this.getConn();
            // 2.创建sql
            PreparedStatement pstm = this.createpstm(sql, conn);
            // 3.执行查询 获得结果
            ResultSet rst = this.executeQuery(pstm, value);
            // 4.解析结果
            List<Object> resList = this.parseResultSet(rst, rowMapper);

            // 5.关闭
            resultsetClose(rst);
            pstmClose(pstm);
            connClose(conn);
            return resList;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    private void connClose(Connection conn) throws SQLException {
        conn.close();
    }

    private void pstmClose(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private void resultsetClose(ResultSet rst) throws SQLException {
        rst.close();
    }

    private ResultSet executeQuery(PreparedStatement pstm, Object[] value) throws SQLException {
        for (int i = 0; i < value.length; i++) {
            pstm.setObject(i, value[i]);
        }
        return pstm.executeQuery();
    }

    private PreparedStatement createpstm(String sql, Connection conn) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }


}
```

```java
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws Exception;


}
```

```java
public class Menber {

    private  String name;
    private  String pwd;

   //... getset 构造方法省略
}
```

```java
public class MenberDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDatasource());

    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    public static void main(String[] args)throws Exception {

        MenberDao menberDao = new MenberDao();
        List<Object> query = menberDao.query();
        System.out.println();
    }

    private static DataSource getDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }


    public List<Object> query() throws Exception {
        String sql = "select * from t_menber";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Menber>() {
            @Override
            public Menber mapRow(ResultSet rs, int rowNum) throws Exception {
                Menber menber = new Menber();
                menber.setName(rs.getString("name"));
                menber.setPwd(rs.getString("pwd"));
                return menber;
            }
        }, new Object[]{});
    }

}
```

- `JdbcTemplate#executeQuery(java.lang.String, com.huifer.design.template.RowMapper<?>, java.lang.Object[])` 这个方法中注意, 使用接口 RowMapper 来实现用户的自定义，用户需要自己实现数据处理操作。





### 总结

1. 模板模式固定了一套流程，我们可以修改其中的一部分流程来达到自定义的效果



## 委派模式

### 定义

> 在委派模式（Delegate）中，有两个或多个对象参与处理同一个请求，接受请求的对象将请求委派给其他对象来处理。



### 代码讲解

- 在一个开发项目中有下图所示的一个结构，项目经理就是委派任务的人，将任务分发给前端工程师、后端工程师、运维工程师。在委派任务时需要根据不同的任务发给不同的工程师。

```mermaid
graph TD;
	项目经理 --> 前端工程师
	项目经理 --> 后端工程师
	项目经理 --> 运维工程师
	
	

```



```java
public interface Dev {

    /**
     * 工作
     */
    void work(String s);

}

public class HD implements Dev {

    @Override
    public void work(String s) {
        System.out.println("后端工程师开始工作");
        System.out.println("后端任务 ： " + s);
    }
}

public class QD implements Dev {

    @Override
    public void work(String s) {
        System.out.println("前端工程师开始工作");
        System.out.println("前端任务 ： " + s);
    }
}

public class YW implements Dev {

    @Override
    public void work(String s) {
        System.out.println("运维工程师开始工作");
        System.out.println("运维任务 ： " + s);
    }
}

```

- 负责人

  ```java
  public class XMFZR {
  
  
      private HashMap<String, Dev> map = new HashMap<>();
  
      public XMFZR() {
          map.put("前端", new QD());
          map.put("后端", new HD());
          map.put("运维", new YW());
      }
  
      public static void main(String[] args) {
  // 1. 确认一个任务
          String s = "这是一个随机任务";
  
          XMFZR xmfzr = new XMFZR();
  // 2. 模拟委派的过程
          double random = Math.random();
          if (random < 0.3) {
              Dev qd = xmfzr.getMap().get("前端");
              qd.work(s);
          } else if (random > 0.3 & random < 0.6) {
              Dev hd = xmfzr.getMap().get("后端");
              hd.work(s);
          } else {
              Dev yw = xmfzr.getMap().get("运维");
              yw.work(s);
          }
  
  
      }
  
      public HashMap<String, Dev> getMap() {
          return map;
      }
  
  }
  ```

  现目负责人 将具体任务根据不同要求委派给部不同的工作人员,让他们去完成具体的任务






- [spring-mvc 中的DispatcherServlet ](https://github.com/huifer/java-ebook/blob/master/ch-2/springMvc.md#spring-mvc%E5%A4%A7%E8%87%B4%E6%B5%81%E7%A8%8B%E6%BA%90%E7%A0%81%E7%BF%BB%E9%98%85)也是一种委派模式.详细请查看文章.



### 总结

1. 委派模式时将各个功能分发给不同的人完成,注重的时结果
2. 委派模式内部的复用率相对较高



## 适配器模式

### 定义

> 将一个类的接口转接成用户所期待的。一个适配使得因接口不兼容而不能在一起工作的类能在一起工作，做法是将类自己的接口包裹在一个已存在的类中。

### 代码讲解

- 现在我们在网上能够看到各种类型的登陆方式,比如:QQ登陆\微信登陆\手机登录.但是原始开发系统时使用的方式为 本地数据库 记录的方式来登陆,为此进行拓展,我们需要适配器

- 定义一个旧的登陆方式 账号密码形式

  ```java
  public class Menber {
  
      private String name;
      private String pwd;
      }
  public class ResultMsg {
  
      private int code;
      private Object data;
      private String id;
  }
  public class LoginService {
  
      public static List<Menber> menberList = new ArrayList<>();
  
      public ResultMsg regist(String name, String pwd) {
          Menber menber = new Menber(name, pwd);
          menberList.add(menber);
          return new ResultMsg(200, menber, name);
      }
  
      public ResultMsg login(String name, String pwd) {
  
          Menber m = new Menber(name, pwd);
          if (menberList.contains(m)) {
              return new ResultMsg(200, "登陆成功", name);
          } else {
              return new ResultMsg(400, "登陆失败", name);
          }
      }
  
  }
  	
  
  public class LoginTest {
  
      public static void main(String[] args) {
          LoginService loginService = new LoginService();
          ResultMsg registA = loginService.regist("a", "1");
          System.out.println(registA);
          ResultMsg loginA = loginService.login("a", "1");
          System.out.println(loginA);
  
      }
  
  }
  ```

- 定义一个新的登陆模块需要完成如下内容

  1. 注册
  2. 登陆

  ```java
  public class NewLoginService extends LoginService {
  
      public ResultMsg loginForQQ(String qqNumb) {
          ResultMsg msg = super.regist(qqNumb, "qq默认密码");
          ResultMsg login = super.login(qqNumb, "qq默认密码");
          return login;
      }
  
  }
  
  
  public class LoginTest {
  
      public static void main(String[] args) {
          LoginService loginService = new LoginService();
          ResultMsg registA = loginService.regist("a", "1");
          System.out.println(registA);
          ResultMsg loginA = loginService.login("a", "1");
          System.out.println(loginA);
  
          NewLoginService newLoginService = new NewLoginService();
          ResultMsg resultMsg = newLoginService.loginForQQ("QQ登陆测试");
          System.out.println(resultMsg);
  
          System.out.println(LoginService.menberList);
  
      }
  
  }
  
  ```

  ### 总结

  1. 不改变原始的登陆模块 , 新模块向下兼容