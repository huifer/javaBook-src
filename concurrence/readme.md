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

### 数据竞争
> - 两条或两条以上的线程并发访问**同一个内存区域**,**至少有一条**执行**写操作**,线程没有协调对同一块内存区域的访问

```java
public class DataRace {

    // 输出运算流程：从0开始累加1
    private static Integer calc = 0;

    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                addOne();
            }
        };
        Thread t1 = new Thread(r, "线程01");
        Thread t2 = new Thread(r, "线程02");
        t1.start();
        t2.start();
    }

    private synchronized static void addOneSynchronized() {
        int now = calc;
        calc++;
        System.out.println(Thread.currentThread().getName() + " - 操作前 " + now + " 操作后 " + calc);
    }

    private  static void addOne() {
        int now = calc;
        calc++;
        System.out.println(Thread.currentThread().getName() + " - 操作前 " + now + " 操作后 " + calc);
    }

}
```

- addOneSynchronized 输出
```text
线程01 - 操作前 0 操作后 1
线程01 - 操作前 1 操作后 2
线程01 - 操作前 2 操作后 3
线程01 - 操作前 3 操作后 4
线程01 - 操作前 4 操作后 5
线程02 - 操作前 5 操作后 6
线程02 - 操作前 6 操作后 7
线程02 - 操作前 7 操作后 8
线程02 - 操作前 8 操作后 9
线程02 - 操作前 9 操作后 10
```
- addOne 输出
```text
线程01 - 操作前 0 操作后 1
线程01 - 操作前 2 操作后 3
线程02 - 操作前 1 操作后 2
线程02 - 操作前 4 操作后 5
线程02 - 操作前 5 操作后 6
线程01 - 操作前 3 操作后 4
线程01 - 操作前 7 操作后 8
线程01 - 操作前 8 操作后 9
线程02 - 操作前 6 操作后 7
线程02 - 操作前 9 操作后 10
```

### 临界区
> 有多个线程试图同时访问临界区，那么在有一个线程进入后其他所有试图访问此临界区的线程将被挂起，并一直持续到进入临界区的线程离开。临界区在被释放后，其他线程可以继续抢占，并以此达到用原子方式操作共享资源的目的。
### jvm 中的同步临界区
- 同步作为jvm的特性,功能在于保证两个或两个以上的线程**不会同时执行相同的临界区**,临界区必须**串行方式**访问
- 线程在临界区的时候每一条线程对灵界去的访问都会互斥执行.线程锁: **互斥锁**

![](pic/线程同步锁.png)


### 同步关键字
- synchronized
```java
public class SynchronizedDemo {

    private static Integer mode = 1;
    private static Integer result = 0;

    public static void main(String[] args) {
        Id id = new Id();
        System.out.println(id.getCount());

        Runnable r1 = () -> {
            synchronized (mode) {
                result = mode + 2;
                System.out.println("当前reslut " + result);
            }
        };

        Thread thread1 = new Thread(r1);
        thread1.start();

    }
}

class Id {

    private static int count = 1;

    public synchronized int getCount() {
        return ++count;
    }
}

```


### 活跃性问题
#### 死锁
- 线程1等待线程2持有的资源,线程2等待线程1持有的资源, 资源互斥(每一个资源只有一线程可以操作),导致程序无法正常运行
![](pic/死锁.png)
 
```java
public class DeadlockDemo {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public static void main(String[] args) {
        DeadlockDemo deadlockDemo = new DeadlockDemo();
        Runnable r1 = () -> {
            while (true) {
                deadlockDemo.insMethod1();
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            while (true) {
                deadlockDemo.insMethod2();
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r1, "线程1");
        Thread t2 = new Thread(r2, "线程2");
        t1.start();
        t2.start();
        // 在这个案例中
        // 线程1获取lock1
        // lock1进入临界区 ,lock2 没有进入
        // 线程2获取lock2
        // lock2进入临界区 ,lock1没有进入
        // JVM同步特性,线程1持有lock1锁 线程2持有lock2锁,线程1需要执行lock2的锁(线程2持有),线程2同理
        // 运行结果交替输出 在方法1中，在方法2中

    }

    public void insMethod1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("在方法1中");
            }
        }
    }

    public void insMethod2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("在方法2中");
            }
        }
    }
}
```
- 死锁经常出现在: 多个方法间互相调用,形成环形调用

#### 活锁
- 线程持续重试一个失败的操作 ,导致程序无法正常运行
#### 饿死
- 线程一直被延迟访问所需要的依赖资源
