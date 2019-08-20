# JDK 相关
## == 和 equals 

### ==

```java
public class Run {

    public static void main(String[] args) {

        int i = 1;
        int j = 1;
        System.out.println(i == j); // true

        String a = "b";
        String b = "b";
        System.out.println(a == b); // true
        String c = new String("b") ;
        System.out.println(a == c); // false
    }
}
```

1. == 对于基本类型 `int` `long`  `double` `float` `boolean` `short` `char` 来说对比较的是值是否相等

2. == 对于引用类型 比较的是引用是否相同

   -      String 的创建方式 直接等于字符串 或者 new String(字符串)
     直接创建的字符串他们的引用地址相同 ， 而 new 创建的字符串是开辟新的内存空间

### equals

```java
public class Run {

    public static void main(String[] args) {
        Eq eq1 = new Eq(1);
        Eq eq2 = new Eq(1);

        System.out.println(eq1.equals(eq2)); //false
    }


    private static class Eq{
        public int anInt;

        public Eq(int anInt) {
            this.anInt = anInt;
        }
    }
}
```



```java
String a = "b";
String c = new String("b") ;
System.out.println(a.equals(c));//true
```

#### 为什么 eq1.equals(eq2) 结果是false

- equals是 `java.lang.Object`的一个方法

  ```java
  public boolean equals(Object obj) {
      return (this == obj);
  }
  ```

  `==`根据上述 引用类型对比的是引用是否相同 (地址是否相同)，关键字`new`都是开辟新的内存地址所以它不相同。

#### 为什么a.equals(c)结果是true

```java
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

- `java.lang.String`重写了equals 方法 ， 将`String `分解成 `char`数组 进行比较是否相等

- `java.lang.Integer ` `java.lang.Double`基础类型的包装类也同样重写了`equals`方法







## hashCode相同 equlas就是ture吗？

- **不是**

```java
public class Run {

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = new String("123");
        System.out.println(String.format("s1: %d \t s2: %d", s1.hashCode(), s2.hashCode()));

        System.out.println(s1.equals(s2));
    }
}
```

- 通过`new String()`来进行验证



## final 关键字

1. final 修饰类 ，该类不能被继承

2. final 修饰方法，该方法不能重写

3. final 修饰变量，我们将这个变量称为常量 , 常量必须初始化

   ​	`public static final double PI = 3.14159265358979323846;`


## GC 的两种判定方法：
> - 引用计数: java虚拟机内部有一个引用计数器,当一个类被使用计数器+1,当一个类不再使用(或引用失效)计数器-1 , GC 回收计数器为0的类(对象)
> - 根搜索算法

## 成员变量与局部变量的区别有那些
> - 作用域不同: 局部变量只在函数内,语句内;成员变量在整个类中都有效
> - 默认值: 局部变量没有默认值;成员变量有默认值


## 字符型常量和字符串常量的区别
> - 字符型:char
> - 字符串:String
>

## 构造器 Constructor 是否可被 override
> 不可以,构造方法唯一,一个类只有一个构造方法

## 重载和重写的区别
> - 重载:Overload
>   - 表示同一个类中可以有多个名称相同的方法，但这些方法的参数列表各不相同（即参数个数或类型不同）。
> - 重写:Override
>   - 参数列表,返回类型必须完全相同,修改了执行逻辑

## 创建一个对象用什么运算符? 对象实体与对象引用有何不同?
> 创建用`new` , 对象实体与对象引用的区别:内存地址    
## 一个类的构造方法的作用是什么 若一个类没有声明构造方法, 该程序能正确执行吗 ?
> - 构造方法的作用:对象的创建,类的初始化
> - 没有构造方法可以正常运行,默认有一个空参构造,实现了有参构造后默认空参构造消失



## String , StringBuffer,StringBuilder 

- String 属于不可变对象，即每次操作都会产生新的String 对象
- `StringBuffer` 和 `StringBuilder` 在原有基础上进行操作

### StringBuffer 与StringBuilder 的区别

- `StringBuffer` 线程安全 ， `StringBuilder`是线程不安全的

  - `java.lang.StringBuilder#append(char)`

    ```java
    @Override
    public StringBuilder append(char c) {
        super.append(c);
        return this;
    }
    ```

  - `java.lang.StringBuffer#append(char)`

    ```java
    @Override
    public synchronized StringBuffer append(char c) {
        toStringCache = null;
        super.append(c);
        return this;
    }
    ```



- `StringBuffer` 的方法有 `synchronized` 修饰



## 抽象类必须要有抽象方法吗？

- **不需要**

- 下述代码可以直接运行

  ```java
  public abstract class Absc {
  
      void hello() {
          System.out.println("hello");
      }
  
  }
  ```

  

## 抽象类和普通类的区别

1. **抽象类不能直接实例化，普通类可以直接实例化 (`new`)**
2. **抽象类可以具有抽象方法 ， 普通类不能具有抽象方法**



## 抽象类是否可以final 修饰

- 不能****

- **抽象类是给其他类继承，重写抽象方法来拓展。`final` 修饰后 这个类将不能被继承**



## 接口和抽象类的区别

|            | 接口             | 抽象类             |
| ---------- | ---------------- | ------------------ |
| 实现方式   | `implements`     | `extends`          |
| 构造函数   | 没有构造函数     | 有构造函数         |
| 实现数量   | 可以实现多个接口 | 只能继承一个抽象类 |
| 访问修饰符 | 默认使用`public` | 任意访问修饰符     |



## java中的IO流

- 功能
  - 输入流
  - 输出流
- 类型
  - 子节流
  - 字符流





## Collection 和Collections 的区别

- `java.util.Collection` 是一个接口	
  - 定义了集合对象的一些通用操作
- `java.util.Collections`是一个类
  - 这是一个包装类，提供很多静态方法，不能被实例化，



## List \Set\Map 区别

|      |             | 元素是否有序 | 元素是否重复            |
| ---- | ----------- | ------------ | ----------------------- |
| List |             | 是           | 是                      |
| Set  | AbstractSet | 否           | 否                      |
|      | HashSet     | 否           | 否                      |
|      | TreeSet     | 是           | 否                      |
| Map  | AbstractMap | 否           | Key 唯一 Value 允许重复 |
|      | HashMap     | 否           | Key 唯一 Value 允许重复 |
|      | TreeMap     | 是           | Key 唯一 Value 允许重复 |



## HashMap 和 HashTable 区别

- `put`方法

  - `java.util.HashMap#put`

    ```java
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    
    /**
     * Implements Map.put and related methods
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
    ```

  - `java.util.Hashtable#put`

    ```java
    public synchronized V put(K key, V value) {
        // Make sure the value is not null
        if (value == null) {
            throw new NullPointerException();
        }
    
        // Makes sure the key is not already in the hashtable.
        Entry<?,?> tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        @SuppressWarnings("unchecked")
        Entry<K,V> entry = (Entry<K,V>)tab[index];
        for(; entry != null ; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                V old = entry.value;
                entry.value = value;
                return old;
            }
        }
    
        addEntry(hash, key, value, index);
        return null;
    }
    ```

### 存储

- HashMap允许key 和 value 为Null

- HashTable不允许key 和 value 为Null

### 线程安全 

- HashMap 线程不安全
- HashTable 线程安全



## ArrayList & LinkedList 的区别

### 数据结构

- ArrayList

  `transient Object[] elementData;` 数组

- LinkedList

  - ```JAVA
    transient Node<E> first;
    transient Node<E> last;
    
        private static class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;
    
            Node(Node<E> prev, E element, Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }
    ```

    双向链表

### 访问数据

- ArrayList 索引访问

  - ```java
    public E get(int index) {
        rangeCheck(index);
    
        return elementData(index);
    }
        E elementData(int index) {
            return (E) elementData[index];
        }
    ```

- LinkedList遍历访问

  - ```java
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
        Node<E> node(int index) {
            // assert isElementIndex(index);
    
            if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x;
            }
        }
    ```

### 删除数据

- ArrayList

  - 遍历直到是删除元素进行删除

    ```java
    public E remove(int index) {
        rangeCheck(index);
    
        modCount++;
        E oldValue = elementData(index);
    
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    
        return oldValue;
    }
    ```

- LinkedList

  - 修改节点即可

    ```java
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }
        E unlink(Node<E> x) {
            // assert x != null;
            final E element = x.item;
            final Node<E> next = x.next;
            final Node<E> prev = x.prev;
    
            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }
    
            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }
    
            x.item = null;
            size--;
            modCount++;
            return element;
        }
    ```

## ArrayList 和Vector 区别

### 线程安全

- ArrayList 线程不安全	

  ```java
  public boolean add(E e) {
      ensureCapacityInternal(size + 1);  // Increments modCount!!
      elementData[size++] = e;
      return true;
  }
  ```

- Vector线程安全 `synchronized`关键字

  ```java
  public synchronized boolean add(E e) {
      modCount++;
      ensureCapacityHelper(elementCount + 1);
      elementData[elementCount++] = e;
      return true;
  }
  ```

### 扩容

- ArrayList扩大50%

  ```java
  int newCapacity = oldCapacity + (oldCapacity >> 1);
  ```

- Vector扩大100%

  ```java
  int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                   capacityIncrement : oldCapacity);
  ```



## Queue 中poll() 和 remove() 



```java
    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();
```

### 相同点

1. 从queue中删除第一个元素，返回第一个元素

### 不同点

1. 异常抛出

   `remove()` 如果元素没有抛出`NoSuchElementException`

   `poll()`则返回null





## 集合如何设置不可修改

- `java.util.Collections#unmodifiableCollection`

```jaa
public class UnchangeableGather {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        Collection<Integer> integers1 = Collections.unmodifiableCollection(integers);
        integers1.add(1);
        System.out.println(integers.size());
    }

}
```



## 并发与并行的区别

- 并行
  - 多个处理器同时处理多个任务
- 并发
  - 多个任务在同一个cpu核上按照时间片轮流执行



## 线程创建方式

1. 继承`Thread` 重写 `run`
2. 实现`Runnable` 接口
3. 实现 `Callable`接口



## 线程状态

`java.lang.Thread.State`

```java
public enum State {
	// 未启动
    NEW,
	// 执行中
    RUNNABLE,
	// 阻塞中
    BLOCKED,
	// 持久等待
    WAITING,
	// 等待到指定时间重写唤醒
    TIMED_WAITING,
	// 执行完成
    TERMINATED;
}
```



## sleep() 和 wait() 区别

1. 类
   1. `java.lang.Thread#sleep(long)`
   2. `java.lang.Object#wait()`
2. 锁的释放
   1. sleep不释放
   2. wait释放

## run() 和 start() 区别

1. 调用次数
   1. `run()`没有限制
   2. `start()`只能调用一次
2. 对应操作项
   1. `run()`对应线程运行时的代码
   2. `start()`对应线程



## notify() 和 notifyAll()区别

- `notify()`只会唤醒一个线程
- `notifyAll()`唤醒所有线程





## 线程池创建
位于 `java.util.concurrent.Executors` 下
- newFixedThreadPool
- newWorkStealingPool
- newSingleThreadExecutor
- newCachedThreadPool
- newSingleThreadScheduledExecutor
- newScheduledThreadPool


## 线程池状态
`java.util.concurrent.ThreadPoolExecutor`
```java
    // 能接受新提交的任务，并且也能处理阻塞队列中的任务；
    private static final int RUNNING    = -1 << COUNT_BITS;
	//	关闭状态，不再接受新提交的任务，但却可以继续处理阻塞队列中已保存的任务。在线程池处于 RUNNING 状态时，调用 shutdown()方法会使线程池进入到该状态。（finalize() 方法在执行过程中也会调用shutdown()方法进入该状态）
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
	//   不能接受新任务，也不处理队列中的任务，会中断正在处理任务的线程。在线程池处于 RUNNING 或 SHUTDOWN 状态时，调用 shutdownNow() 方法会使线程池进入到该状态；  
	private static final int STOP       =  1 << COUNT_BITS;
    //如果所有的任务都已终止了，workerCount (有效线程数) 为0，线程池进入该状态后会调用 terminated() 方法进入TERMINATED 状态。
	private static final int TIDYING    =  2 << COUNT_BITS;
    // 在terminated() 方法执行完后进入该状态，默认terminated()方法中什么也没有做。
	private static final int TERMINATED =  3 << COUNT_BITS;
```

进入TERMINATED的条件如下：

- 线程池不是RUNNING状态；
- 线程池状态不是TIDYING状态或TERMINATED状态；
- 如果线程池状态是SHUTDOWN并且workerQueue为空；
- workerCount为0；
- 设置TIDYING状态成功。







## Submit() 和 execute() 区别

| 方法      | runnable 任务 | callable任务 |
| --------- | ------------- | ------------ |
| execute() | 执行          | 不执行       |
| submit()  | 执行          | 执行         |





## 多线程安全如何保证

1. 使用synchronized 
2. 使用Lock
3. 使用安全类



## synchronized 和 volatile 的区别

- volate
  - 变量 修饰符
  - 实现变量的修改可见性，不能保证原子性
  - 不会阻塞线程
- synchronized
  - 类 、方法 、 代码段 修饰符
  - 保证变量的修改可见性， 保证原子性
  - 会阻塞线程





## synchronized 和 lock 的区别

- synchronized
  - 给类 、方法、代码段 添加锁
  - 不需要释放锁，这个行为JVM自动实现
  - 不能确定是否获取锁

- lock
  - 给代码段添加锁
  - 需要手动获取锁， 释放锁否则会陷入死锁的问题（`unlock`）
  - 可以确定是否成功获取锁



## 串行&并行

### 串行

- 模拟一个spring项目加载配置文件

  ```java
  public class SpringLoad {
  
      public static void main(String[] args) {
          SpringLoad springLoad = new SpringLoad();
          springLoad.load();
      }
  
      private void load() {
          long startTime = System.currentTimeMillis();
          loadProject();
          long endTime = System.currentTimeMillis();
          System.out.printf("加载总共耗时:%d 毫秒\n", endTime - startTime);
      }
  
  
      private void loadProject() {
          loadSpringMvc();
          loadMyBatis();
          loadSpring();
      }
  
  
      private void loadSpringMvc() {
          loadXML("spring-mvc.xml", 1);
      }
  
  
      private void loadMyBatis() {
          loadXML("mybatis.xml", 2);
      }
  
      private void loadSpring() {
          loadXML("spring.xml", 3);
      }
  
  
      /***
       * 加载文件
       * @param xml
       * @param loadSec
       */
      private void loadXML(String xml, int loadSec) {
          try {
              long startTime = System.currentTimeMillis();
              long milliseconds = TimeUnit.SECONDS.toMillis(loadSec);
              Thread.sleep(milliseconds);
              long endTime = System.currentTimeMillis();
  
              System.out.printf("[线程 : %s] 加载%s 耗时: %d 毫秒\n",
                      Thread.currentThread().getName(),
                      xml,
                      endTime - startTime
              );
          } catch (Exception e) {
              e.printStackTrace();
  
          }
      }
  
  }
  ```

```
[线程 : main] 加载spring-mvc.xml 耗时: 1001 毫秒
[线程 : main] 加载mybatis.xml 耗时: 2000 毫秒
[线程 : main] 加载spring.xml 耗时: 3000 毫秒
加载总共耗时:6017 毫秒	
```

可以发现串行消费时间 = 各个方法的总和



### 并行 

```java
public class SpringLoad2  {

    public static void main(String[] args) {
        SpringLoad2 springLoad2 = new SpringLoad2();
        springLoad2.loadProject();
    }

    public void loadProject() {
        try {
            long start = System.currentTimeMillis();
            ExecutorService service = Executors.newFixedThreadPool(3);
            CompletionService completionService = new ExecutorCompletionService(service);

            completionService.submit(this::loadSpringMvc, null);
            completionService.submit(this::loadMyBatis, null);
            completionService.submit(this::loadSpring, null);


            int count = 0;
            while (count < 3) {
                if (completionService.poll() != null) {
                    count++;
                }
            }

            service.shutdown();
            System.out.println(System.currentTimeMillis() - start);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    private void loadSpringMvc() {
        loadXML("spring-mvc.xml", 1);
    }


    private void loadMyBatis() {
        loadXML("mybatis.xml", 2);
    }

    private void loadSpring() {
        loadXML("spring.xml", 3);
    }


    /***
     * 加载文件
     * @param xml
     * @param loadSec
     */
    private void loadXML(String xml, int loadSec) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(loadSec);
            Thread.sleep(milliseconds);
            long endTime = System.currentTimeMillis();

            System.out.printf("[线程 : %s] 加载%s 耗时: %d 毫秒\n",
                    Thread.currentThread().getName(),
                    xml,
                    endTime - startTime
            );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
```

- 最长的消耗时间为运行总时长



## 反射是什么

程序运行时可以指定该类的方法、属性，并且可以调用

```java
public class Run {

    public static void main(String[] args) throws Exception {
        Cc cccc = new Cc();

        Class<? extends Cc> aClass = cccc.getClass();

        Method setAname = aClass.getDeclaredMethod("setAname", String.class);

        Method sayHi = aClass.getDeclaredMethod("sayHi", null);
        sayHi.setAccessible(true);

        setAname.invoke(cccc, "张三");
        sayHi.invoke(cccc, null);

    }

    private static class Cc {

        private String aname;

        private void sayHi() {
            System.out.println("hello" + aname);
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }

}
```





## throw 和 throws 区别

- throw 真实抛出一个异常
- throws 声明式抛出一个异常 (可能发生)



## try catch finally 可以省略那些

`catch` 和 `finally` 两个只能省略其中一个 ，`try`必须保留



## try catch finally 中， catch 有return ，finally 执行吗

- **执行 ， 在catch中的return 结束后执行**

