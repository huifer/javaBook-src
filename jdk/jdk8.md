# jdk8
## lambda
- java8之前，不能将一个函数作为参数，也不能返回一个函数

### 初识lambda

```jav
public class JFrameDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("jf");
        JButton button = new JButton("button");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 开发人员真正关注的内容
                System.out.println("button click");
            }
        });

        jf.add(button);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
```

- 上述代码中 ActionListener是一个匿名类，再JDK8中可以使用如下写法

  ```java
  public class JFrameDemo {
  
      public static void main(String[] args) {
          JFrame jf = new JFrame("jf");
          JButton button = new JButton("button");
          button.addActionListener(e -> {
              // 开发人员真正关注的内容
              System.out.println("button click");
          });
  
          jf.add(button);
          jf.pack();
          jf.setVisible(true);
          jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  
      }
  }
  ```

### lambda的基本形式

```\
(参数列表)->{
    执行体
}
```

### 函数式接口

- `java.lang.FunctionalInterface`

  > 什么是函数式接口？
  >
  > 1. 如果一个接口只有一个抽象方法，那么该接口是要给函数式接口
  > 2. 如果再一个接口上使用`@FunctionalInterface`注解，编译器会根据拟定好的规则来判断，符合规则通过，否则抛出异常
  > 3. 建议：一个接口只有一个抽象方法，虽然不用`@FunctionalInterface`注解标注也会被编译器看作是函数式接口，但不易阅读还是加上`@FunctionalInterface`注解



```java
package com.huifer.jdk.jdk8.lambda;


public class Demo01 {

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        demo01.myInterfaceTest(() -> {
            System.out.println("hello");
        });
    }

    public void myInterfaceTest(MyInterface myInterface) {
        myInterface.test();
    }
}

@FunctionalInterface
interface MyInterface {
    void test();
}
```



### 从ForEach看函数式

```java
public class Demo02 {
    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 2, 3);
        lists.forEach(
                new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                }
        );
    }
}
```

- `forEach` 

  - default method 默认方法

  ```java
  public interface Iterable<T> {
    
      Iterator<T> iterator();
  
      // 规定了一种默认的行为： 遍历这个传入的对象(集合)
      default void forEach(Consumer<? super T> action) {
          Objects.requireNonNull(action);
          for (T t : this) {
              action.accept(t);
          }
      }
  
      default Spliterator<T> spliterator() {
          return Spliterators.spliteratorUnknownSize(iterator(), 0);
      }
  }
  ```

- `Consumer`

  - 此处是一个操作模式，没有返回值，但是可能会对其中的值进修改

  ```java
  @FunctionalInterface
  public interface Consumer<T> {
  
      void accept(T t);
  
      default Consumer<T> andThen(Consumer<? super T> after) {
          Objects.requireNonNull(after);
          return (T t) -> { accept(t); after.accept(t); };
      }
  }
  ```

- lambda 表达式修改

    ```java
    public class Demo02 {
        public static void main(String[] args) {
            List<Integer> lists = Arrays.asList(1, 2, 3);
            lists.forEach(
                    integer -> System.out.println(integer)
            );
        }
    }
    ```



### lambda表达式是对象

- **单独给出 `()->{}`无法确定具体是什么**

```java
package com.huifer.jdk.jdk8.lambda;

public class Demo03 {
    public static void main(String[] args) {
        Interface01 i1 = () -> {
        };
        Interface02 i2 = () -> {
        };

        System.out.println(i1.getClass().getInterfaces()[0]);
        System.out.println(i2.getClass().getInterfaces()[0]);

    }
}

@FunctionalInterface
interface Interface01 {
    void demo();
}


@FunctionalInterface
interface Interface02 {
    void demo();
}
```



## stream

- 当以一个`List<Integer>` 对每一个数进行`+1`操作以及`Math.pow(i,i)`

```java
public class Demo01 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> collect = list.stream().map(i -> i + 1).collect(Collectors.toList());
        System.out.println(collect);
        List<Double> collect2 = list.stream().map(
                Demo01::apply
        ).collect(Collectors.toList());
        System.out.println(collect2);

    }

    private static Double apply(Integer i) {
        return Math.pow(i, i);
    }
}
```