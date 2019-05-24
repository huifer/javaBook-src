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