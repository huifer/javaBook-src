# Spring Log
- Author: [HuiFer](https://github.com/huifer)
- 源码阅读仓库: [huifer-spring](https://github.com/huifer/spring-framework)


## Log
- 源码路径:`org.apache.commons.logging.Log`
- spring jcl 模块内,采用的方式是工厂模式+适配器模式
```java
public interface Log {


    boolean isFatalEnabled();

    boolean isErrorEnabled();

    boolean isWarnEnabled();

    boolean isInfoEnabled();

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void fatal(Object message);

    void fatal(Object message, Throwable t);

    void error(Object message);

    void error(Object message, Throwable t);

    void warn(Object message);

    void warn(Object message, Throwable t);

    void info(Object message);

    void info(Object message, Throwable t);

    void debug(Object message);
    
    void debug(Object message, Throwable t);

    void trace(Object message);
  
    void trace(Object message, Throwable t);

}
```

## LogFactory
- 日志工厂,根据名称,或者 clazz 创建日志,最后指向`LogAdapter.createLog`方法
```java
public abstract class LogFactory {

    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String name) {
        return LogAdapter.createLog(name);
    }

    @Deprecated
    public static LogFactory getFactory() {
        return new LogFactory() {
        };
    }
    
    @Deprecated
    public Log getInstance(Class<?> clazz) {
        return getLog(clazz);
    }

    @Deprecated
    public Log getInstance(String name) {
        return getLog(name);
    }

}
```

## LogAdapter
### createLog 根据logApi配置情况创建日志
```java
public static Log createLog(String name) {
        // 创建不同的日志处理器
        switch (logApi) {
            case LOG4J:
                // LOG4J
                return Log4jAdapter.createLog(name);
            case SLF4J_LAL:
                return Slf4jAdapter.createLocationAwareLog(name);
            case SLF4J:
                return Slf4jAdapter.createLog(name);
            default:
                // Defensively use lazy-initializing adapter class here as well since the
                // java.logging module is not present by default on JDK 9. We are requiring
                // its presence if neither Log4j nor SLF4J is available; however, in the
                // case of Log4j or SLF4J, we are trying to prevent early initialization
                // of the JavaUtilLog adapter - e.g. by a JVM in debug mode - when eagerly
                // trying to parse the bytecode for all the cases of this switch clause.
                return JavaUtilAdapter.createLog(name);
        }
    }
```
