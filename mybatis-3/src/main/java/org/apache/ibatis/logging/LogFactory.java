/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.logging;

import java.lang.reflect.Constructor;

/**
 * <p>日志工厂，实现内容:</p>
 * <ol>
 *     <li>org.slf4j.Logger 日志框架 slf4j</li>
 *     <li>org.apache.commons.logging.Log 日志框架 apache</li>
 *     <li>org.apache.logging.log4j.Logger 日志框架 log4j2</li>
 *     <li>org.apache.log4j.Logger 日志框架 log4j </li>
 *     <li>java.util.logging.Logger 日志框架,JDK的logger</li>
 *
 * </ol>
 * @author Clinton Begin
 * @author Eduardo Macarron
 */
public final class LogFactory {

    /**
     * Marker to be used by logging implementations that support markers.
     */
    public static final String MARKER = "MYBATIS";

    private static Constructor<? extends Log> logConstructor;

    /**
     * 日志的实现类的具体选择
     */
    static {
        // slf4j 日志
        tryImplementation(LogFactory::useSlf4jLogging);
        // apache 日志
        tryImplementation(LogFactory::useCommonsLogging);
        // log4j2 日志
        tryImplementation(LogFactory::useLog4J2Logging);
        // log4 日志
        tryImplementation(LogFactory::useLog4JLogging);
        // JDK 日志
        tryImplementation(LogFactory::useJdkLogging);
        // 空 日志
        tryImplementation(LogFactory::useNoLogging);
    }

    /**
     * 私有化构造方法,这是一个单例
     */
    private LogFactory() {
        // disable construction
    }

    public static Log getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static Log getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Throwable t) {
            throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
        }
    }

    public static synchronized void useCustomLogging(Class<? extends Log> clazz) {
        setImplementation(clazz);
    }

    public static synchronized void useSlf4jLogging() {
        setImplementation(org.apache.ibatis.logging.slf4j.Slf4jImpl.class);
    }

    public static synchronized void useCommonsLogging() {
        setImplementation(org.apache.ibatis.logging.commons.JakartaCommonsLoggingImpl.class);
    }

    public static synchronized void useLog4JLogging() {
        setImplementation(org.apache.ibatis.logging.log4j.Log4jImpl.class);
    }

    public static synchronized void useLog4J2Logging() {
        setImplementation(org.apache.ibatis.logging.log4j2.Log4j2Impl.class);
    }

    public static synchronized void useJdkLogging() {
        setImplementation(org.apache.ibatis.logging.jdk14.Jdk14LoggingImpl.class);
    }

    public static synchronized void useStdOutLogging() {
        setImplementation(org.apache.ibatis.logging.stdout.StdOutImpl.class);
    }

    public static synchronized void useNoLogging() {
        setImplementation(org.apache.ibatis.logging.nologging.NoLoggingImpl.class);
    }

    /**
     * 选择具体的日志实现
     */
    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                // run()? 似乎违背了代码的语义, 看静态方法.静态方法多行同类型的操作我认为是一个多线程
                runnable.run();
            } catch (Throwable t) {
                // ignore
            }
        }
    }

    /**
     * 选择具体的日志实现
     */
    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            Constructor<? extends Log> candidate = implClass.getConstructor(String.class);
            Log log = candidate.newInstance(LogFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new LogException("Error setting Log implementation.  Cause: " + t, t);
        }
    }

}
