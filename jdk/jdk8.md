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



### `collect`

```java
public class Dmo02 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        // 转换成string数组
        // 方法引用
        String[] strings = stringStream.toArray(String[]::new);

        System.out.println(strings);

        List<String> collect = Arrays.stream(strings).collect(Collectors.toList());


        ArrayList<String> collect1 = Arrays.stream(strings).collect(
                () -> new ArrayList<String>(), // 返回类型
                (list, item) -> list.add(item), // 将item传入list中
                (result, list) -> result.addAll(list) // 将list全部追加到result中返回
        );

        System.out.println(collect1);
    }
}

```

- `java.util.stream.Stream#collect(java.util.function.Supplier<R>, java.util.function.BiConsumer<R,? super T>, java.util.function.BiConsumer<R,R>)`

  ```java
  <R> R collect(Supplier<R> supplier, // 结果容器
                BiConsumer<R, ? super T> accumulator, // 和结果容器相关联的一个函数，用来合并
                BiConsumer<R, R> combiner// 结果返回
                );
  ```



- `java.util.stream.Collectors#toList`

  ```java
  public static <T>
  Collector<T, ?, List<T>> toList() {
      return new CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
                                 (left, right) -> { left.addAll(right); return left; },
                                 CH_ID);
  }
  ```

  

### `Collectors.toCollection`

- 可用来做数据类型转换，也可以使用`toSet`这类方法

```java
public class Demo03 {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        ArrayList<String> collect = stringStream.collect(Collectors.toCollection(ArrayList::new));
HashSet<String> collect1 = stringStream.collect(Collectors.toCollection(HashSet::new));
    }
}
```





### `Optional`

```java
public class Demo04 {
    public static void main(String[] args) {
//        Stream<String> stringStream = Stream.generate(UUID.randomUUID()::toString);
        Stream<String> stringStream = null;

        stringStream.findFirst().get();

        if (stringStream.findFirst().isPresent()) {
            // TODO:.....
        }
        stringStream.findFirst().ifPresent(System.out::println);

    }
}
```

- Optional这个类存在一个判断是否为空，直接get可能存在`Exception in thread "main" java.lang.NullPointerException`异常，为了规避这个异常使用`ifPresent`方法进行判断在进行后续操作

### `iterate` & `limit`

- `iterate`无限流
- `limit`限制次数

```java
public class Demo05 {
    public static void main(String[] args) {
        Stream.iterate(0, item -> item + 2).limit(10);
    }
}
```

### 流的关闭与使用

- 下列代码存在什么问题？

```java
public class Demo05 {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.iterate(1, item -> item + 1).limit(10);
        int sum = integerStream.filter(integer -> integer > 2).mapToInt(x -> x * 2).skip(2).limit(2).sum();
        System.out.println(sum);
        IntSummaryStatistics intSummaryStatistics = integerStream.filter(integer -> integer > 2).mapToInt(x -> x * 2).summaryStatistics();

        System.out.println(intSummaryStatistics.getMin());
    }
}
```

- 再sum这一行结束后`integerStream`流被关闭了

  - 重复使用一个流会抛出异常
  - 流关闭会抛出异常

  ```
  32
  Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
  	at java.base/java.util.stream.AbstractPipeline.<init>(AbstractPipeline.java:203)
  	at java.base/java.util.stream.ReferencePipeline.<init>(ReferencePipeline.java:94)
  	at java.base/java.util.stream.ReferencePipeline$StatelessOp.<init>(ReferencePipeline.java:696)
  	at java.base/java.util.stream.ReferencePipeline$2.<init>(ReferencePipeline.java:165)
  	at java.base/java.util.stream.ReferencePipeline.filter(ReferencePipeline.java:164)
  	at com.huifer.jdk.jdk8.stearm.Demo05.main(Demo05.java:17)
  
  ```

  ```java
  Stream<Integer> integerStream1 = integerStream.filter(i -> i > 2);
  System.out.println(integerStream1);
  Stream<Integer> distinct = integerStream1.distinct();
  Stream<Integer> integerStream2 = distinct.filter(i -> 2 > 3);
  System.out.println(integerStream2);
  ```

- 正确代码如下

  ```java
  public class Demo05 {
      public static void main(String[] args) {
          Stream<Integer> integerStream = Stream.iterate(1, item -> item + 1).limit(10);
          IntSummaryStatistics intSummaryStatistics = integerStream.filter(integer -> integer > 2).mapToInt(x -> x * 2).summaryStatistics();
  
          System.out.println(intSummaryStatistics.getMin());
      }
  }
  ```

  上述代码存在删除了一部分原始的需求(求和操作) , 将两种或者多种进行多次分装函数

### 流的中间操作

- stream 的中间操作需要有一个终止操作才会有中间操作执行
  - 中间操作判断：查看返回值是否为`stream`
  - 终止操作
    - collect
    - foreach
    - ...

```java
public class Demo06 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4).map(i -> {
            System.out.println("中间方法调用");
            return i;
        }).collect(Collectors.toList());
        

//                .forEach(System.out::println);
    }
}
```

