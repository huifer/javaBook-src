# java 并发
## Thread & Runnable
- 继承**java.lang.Thread**类
- 实现**java.lang.Runnable**接口


### 开始一个简单线程
需求:
- 创建一个线程名称
- 获取一个线程名称
#### Thread
```java
public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println("hello Thread");
    }


    public static void main(String[] args) {
        HelloThread th = new HelloThread();
        th.setName("测试线程名称");
        th.run();
        System.out.println(th.getName());
    }

}
```
- 运行结果
```text
hello Thread
测试线程名称
```
#### Runnable
```java
public class HelloRunnable {

    public static void main(String[] args) {
        Runnable runnable = runnableDemo();
        Thread t = new Thread(runnable, "测试线程");
        t.run();
        System.out.println(t.getName());

    }

    private static Runnable runnableDemo() {
        Runnable r = () -> System.out.println("hello runnable");
        return r;
    }
}
```
- 运行结果
```text
hello runnable
测试线程
```

### 线程状态
- java.lang.Thread.State 有这个枚举定义
- NEW:线程没有执行
- RUNNABLE:线程在JVM中执行
- BLOCKED:线程阻塞,等待一个监听锁
- WAITING:等待另一个线程执行
- TIMED_WAITING:线程在一定时间内等待另一个线程执行
- TERMINATED:线程退出
- 通过 java.lang.Thread.getState() 方法获取

### 线程优先级
- 最大优先级、最小优先级、默认优先级
```java
 /**
     * The minimum priority that a thread can have.
     * 最小优先级
     */
    public final static int MIN_PRIORITY = 1;

   /**
     * The default priority that is assigned to a thread.
     * 默认优先级
     */
    public final static int NORM_PRIORITY = 5;

    /**
     * The maximum priority that a thread can have.
     * 最大优先级
     */
    public final static int MAX_PRIORITY = 10;
```
- 设置优先级
- java.lang.Thread.setPriority 方法
- 参数值在1-10之间
```java
    public final void setPriority(int newPriority) {
        ThreadGroup g;
        checkAccess();
        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
            throw new IllegalArgumentException();
        }
        if((g = getThreadGroup()) != null) {
            if (newPriority > g.getMaxPriority()) {
                newPriority = g.getMaxPriority();
            }
            setPriority0(priority = newPriority);
        }
    }
```

### 守护线程 & 非守护线程
- java.lang.Thread.setDaemon() 方法设置守护线程
- java.lang.Thread.isDaemon() 查看是否是守护线程

```java
    /* Whether or not the thread is a daemon thread. */
    private boolean     daemon = false;
```

### 线程启动
- java.lang.Thread.start() 方法

### 完整的一个案例
```java
public class ThreadDemo001 {

    public static void main(String[] args) {
        Runnable r1 = () -> {
            Thread thread = Thread.currentThread();
            System.out.printf("runnable 内线程：%s 是否存活: %s 状态 %s",
                    thread.getName(),
                    thread.isAlive(),
                    thread.getState()
            );
            System.out.println();

        };

        Thread t1 = new Thread(r1, "thread-1");
        Thread t2 = new Thread(r1, "thread-2");

        // 设置t1 作为守护线程
        t1.setDaemon(true);

        System.out.printf("线程：%s 是否存活: %s 状态 %s",
                t1.getName(),
                t1.isAlive(),
                t1.getState()
        );
        System.out.println();

        System.out.printf("线程：%s 是否存活: %s 状态 %s",
                t2.getName(),
                t2.isAlive(),
                t2.getState()
        );
        System.out.println();
        t1.start();
        t2.start();
    }

}
```
### 其他操作
#### 中断线程 
- java.lang.Thread.interrupt()
    - 当线程在阻塞状态中 sleep 或 join 方法,阻塞状态清除
- java.lang.Thread.interrupted()
    - 当前线程是否中断
- java.lang.Thread.isInterrupted()
    - 当前线程是否中断 
        
        
```java
public class ThreadInterrup {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            int count = 0;
            while (!Thread.interrupted()) {
                System.out.println(name + ":" + count++);
            }
        };

        Thread t1 = new Thread(r,"thread-01");
        Thread t2 = new Thread(r, "thread-02");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1L);
        // 把线程1打断
        t1.interrupt();
        TimeUnit.SECONDS.sleep(2L);
        t2.interrupt();

    }
}

```

#### 等待线程
- java.lang.Thread.join()等待直到线程死亡
```java
public class ThreadJoin {

    private static Long res;

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        };


        Thread t = new Thread(r);
        t.start();
        try {

            t.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("stop");

    }


}

```

#### 线程睡眠
- java.lang.Thread.sleep(long) 线程睡眠 毫秒
```java
public class ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            int count = 0;
            while (!Thread.interrupted()) {
                System.out.println(name + ":" + count++);
            }
        };

        Thread t1 = new Thread(r,"thread-01");
        Thread t2 = new Thread(r, "thread-02");
        t1.start();
        t2.start();
        try {
            Thread.sleep(10);
            t1.interrupt();
            t2.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
```
