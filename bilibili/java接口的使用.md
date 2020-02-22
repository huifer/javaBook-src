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

- 分别实现独立的操作. 在调用时分别调出不同的逻辑即可
```java
package com.huifer.bilibili.inteface;

import com.huifer.bilibili.inteface.impl.ExcelOperation;
import com.huifer.bilibili.inteface.impl.MySqlOperation;
import com.huifer.bilibili.inteface.impl.PPTOperation;
import com.huifer.bilibili.inteface.impl.RedisOperation;

public class Run {
    public static void main(String[] args) {
        int code = 1;
        doWork(code);
    }

    public static void doWork(int code) {
        if (code == 1) {
            PPTOperation pptOperation = new PPTOperation();
            pptOperation.create();
        }
        else if (code == 2) {
            ExcelOperation excelOperation = new ExcelOperation();
            excelOperation.create();
        }
        else if (code == 3) {
            RedisOperation redisOperation = new RedisOperation();
            redisOperation.importData();
        }
        else if (code == 4) {
            MySqlOperation mySqlOperation = new MySqlOperation();
            mySqlOperation.importData();
        }
    }
}

```

- 这就是第一版本的实现了
### 第二版本
- 由于第一版接口没有彻底应用,有点分散对此进行改进. 再向上抽出一层接口,通过关键字`extends`
- 为什么再向上提取一层
    1. 我们的操作operation会存在更多的操作逻辑每一个级别的操作逻辑都不相同,指每一个接口的实现方法不同,因此向上提取一层主要做统一管理使用
    
    
    
```java
 public static final HashMap<Integer, Operation> OPERATION_HASH_MAP = new HashMap<Integer, Operation>();

    static {
        OPERATION_HASH_MAP.put(1, new PPTOperation());
        OPERATION_HASH_MAP.put(2, new ExcelOperation());
        OPERATION_HASH_MAP.put(3, new RedisOperation());
        OPERATION_HASH_MAP.put(4, new MySqlOperation());
    }

    public static void doWork2(int code, String workType) {
        Operation operation = OPERATION_HASH_MAP.get(code);
        if (operation instanceof PPTOperation) {
            if (workType.equals("create")) {
                ((PPTOperation) operation).create();
            }
        }
        else if (operation instanceof ExcelOperation) {
            if (workType.equals("create")) {
                ((ExcelOperation) operation).create();
            }
        }
        else if (operation instanceof RedisOperation) {
            if (workType.equals("redis_import")) {
                ((RedisOperation) operation).importData();
            }
        }
        else if (operation instanceof MySqlOperation) {
            if (workType.equals("mysql_import")) {
                ((MySqlOperation) operation).importData();
            }
        }
    }
```

- 相对于第一版层次比较清晰. 代码量确实会增加或者说没有变