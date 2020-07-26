# 手写 spring mvc 基于注解
> author: huifer

## 前置知识
- 在 spring 中我们会有如下几个注解来帮助我们定义 web-mvc 的语义
    1. Controller
    1. Service
    1. RequestParam
    1. Autowired
    1. RequestMapping
- 这些注解相比大家都使用过在这里就不具体展开描述了. 在后面的开发中我们再来细说    

## 配置篇
- web.xml 的配置
- 在 web.xml 中我们需要配置
    1. servlet-class
    1. spring的配置(伪)
    1. url-pattern
    
- spring 配置这里简化为一个包的扫描路径. component-scan

- 配置详情
- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
  version="4.0">
  <display-name>HuiFer web application</display-name>
  <servlet>
    <servlet-name>HuiFer mvc</servlet-name>
    <servlet-class>org.huifer.spring.servlet.v1.HFDispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath*:application.properties</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>HuiFer mvc</servlet-name>
    <url-pattern>/*</url-pattern>
  </servlet-mapping>
</web-app>
```

- application.properties

```properties
scanPackage=org.huifer.spring
```

## 注解篇


- HFAutowired

```java

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD,
    ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HFAutowired {

  String value() default "";

}
```

- HFController

```java
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HFController {

}

```

- HFRequestMapping

```java
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HFRequestMapping {

  String name() default "";
}
```

- HFRequestParam

```java

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HFRequestParam {

  String name() default "";
}
```

- HFService

```java

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HFService {

  String name() default "";

}

```

- 注解篇没什么可以多说的. 只是定义一些语义标记. 接下来就是具体的实现了


## servlet 篇
- 开发流程
    1. 读取web.xml中的配置,读取包扫描路径
    1. 根据包扫描路径加载类命.
    1. 实例化类.(service注解) 
        1. 根据名称注入
        1. 根据类型注入
    1. 读取 requestMapping 注解
        1. key: url , value: method
    1. 执行http请求
    
    
- 首先继承 `javax.servlet.http.HttpServlet`

- 重写的方法
    1. init
    1. doPost
    1. doGet
- 主要部分都在init方法中, 按照行为拆分如下几个方法
    1. doLoadConfig 读取配置
    1. doScan 进行包扫描
    1. instance 实例化
    1. autowired 注入
    1. initHandlerMapping url和method映射关系
    
    
    
- 读取配置文件. 通过`getServletConfig`获取 servlet 的配置, 并读取 resource 文件夹中的配置.

```java
  /**
   * 从 web.xml 读取contextConfigLocation <br> 把 {@code classpath*:application.properties} 载入配置
   */
  private void doLoadConfig() {
    InputStream resourceAsStream = null;
    try {

      // 获取servlet的配置
      ServletConfig servletConfig = this.getServletConfig();
      String configInitParameter = servletConfig.getInitParameter(CONTEXT_CONFIG_LOCATION)
          .replace("classpath*:", "");
      // 读取配置文件
      resourceAsStream = this.getClass().getClassLoader()
          .getResourceAsStream(configInitParameter);
      SPRING_CONTEXT_CONFIG.load(resourceAsStream);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (resourceAsStream != null) {
        try {
          resourceAsStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

```

- 包扫描. 递归的扫描基本路径下的class. 放入类名字列表

```java
  /**
   * 包扫描
   */
  private void doScan(String scanPackage) {

    // 类路径
    URL resource = this.getClass().getClassLoader()
        .getResource("/" + scanPackage.replaceAll("\\.", "/"));

    File classPath = new File(resource.getFile());

    for (File file : classPath.listFiles()) {
      if (file.isDirectory()) {
        doScan(scanPackage + "." + file.getName());
      }
      else {
        if (!file.getName().endsWith(".class")) {
          continue;
        }
        else {
          String className = (scanPackage + "." + file.getName()).replace(".class", "");
          classNameList.add(className);
        }
      }
    }
  }

```

- 实例化.
    - 那些类需要实例化. 带有 spring 注解的类需要初始化. 在spring里面是 @Component 这里做例子就直接写死了几个需要初始化的标记接口
        1. HFController
        1. HFService
    - 实例化后的存储
        1. Map 结构毋庸置疑
            - 在spring中我们注入有 byName 和 byType. 我们这个简单的ioc容器也会有
                - byName 的存储方式: key: beanName value: object
            - 通过反射方法`getInterfaces` 我们可以获取这个类实现了那些接口. 因此可以直接进行注入
                - byType 的存储方式: key: interfaceName value:Object

```java
  /**
   * 实例化
   */
  private void instance() {
    if (this.classNameList.isEmpty()) {
      return;
    }
    try {

      for (String clazz : this.classNameList) {
        Class<?> aClass = Class.forName(clazz);

        // 1. 接口的实现类初始化
        if (!aClass.isInterface()) {
          System.out.println(clazz);
          Object o = aClass.newInstance();
          // 1. 带有注解的初始化
          if (aClass.isAnnotationPresent(HFController.class)) {
            IOC_NAME.put(aClass.getSimpleName(), o);
          }
          else if (aClass.isAnnotationPresent(HFService.class)) {
            HFService annotation = aClass.getAnnotation(HFService.class);
            // 名字注入
            if (annotation.name().equals("")) {
              IOC_NAME.put(aClass.getSimpleName(), o);
            }
            else {
              IOC_NAME.put(annotation.name(), o);
            }

            // 类型注入
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
              if (!IOC_NAME.containsKey(anInterface.getName())) {
                IOC_NAME.put(anInterface.getName(), o);
              }
            }
          }

          else {
            continue;
          }
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

```

- 自动注入
    - 从ico容器中获取实例化的对象并且强制设置属性
        1. 那些字段需要呗强制设置? 带有 HFAutowired 注解的字段

```java
private void autowired() {

    if (IOC_NAME.isEmpty()) {
      return;
    }

    for (Entry<String, Object> entry : IOC_NAME.entrySet()) {
      try {

        String k = entry.getKey();
        Object v = entry.getValue();
        Field[] declaredFields = v.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
          // 是否又自动注入的注解
          if (declaredField.isAnnotationPresent(HFAutowired.class)) {
            HFAutowired annotation = declaredField.getAnnotation(HFAutowired.class);
            String beanName = annotation.value().trim();
            // byType 获取具体的实现类
            if (beanName.equals("")) {
              beanName = declaredField.getType().getName();
            }
            declaredField.setAccessible(true);
            declaredField.set(v, IOC_NAME.get(beanName));
          }
          else {
            continue;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();

      }
    }

  }
```

- 绑定url和执行方法
    - 先找到 HFController. 再找到 HFRequestMapping 反射获取所有 method 判断method是否有 HFRequestMapping. 最终组装, 类上面的HFRequestMapping和方法上的HFRequestMapping属性值拼接起来就是url

```java
private void initHandlerMapping() {
    if (IOC_NAME.isEmpty()) {
      return;
    }

    for (Entry<String, Object> entry : IOC_NAME.entrySet()) {
      Class<?> clazz = entry.getValue().getClass();

      if (clazz.isAnnotationPresent(HFController.class)) {
        if (clazz.isAnnotationPresent(HFRequestMapping.class)) {
          HFRequestMapping annotation = clazz.getAnnotation(HFRequestMapping.class);
          String baseUri = annotation.name();

          for (Method method : clazz.getMethods()) {
            HFRequestMapping annotation1 = method.getAnnotation(HFRequestMapping.class);
            if (annotation1 != null) {

              String uri = ("/" + baseUri + "/" + annotation1.name()).replaceAll("/+", "/");
              HandlerMapping.put(uri, method);
            }
          }
        }
      }


    }

  }
```


## 运行时

- 处理请求
    1. 从请求的url转换为method
    1. 参数匹配
        1. 读取method的参数列表,类型列表
        2. 根据类型我们有两个可以直接设置HttpServletRequest,HttpServletResponse
        3. 获取参数的注解. HFRequestParam . 从url中获取对应的名称. 放入参数列表. 
        4. method.invoke 执行. 
        5. 获取method的执行结果. response 写出

```java
private void dispath(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    String requestURI = req.getRequestURI();
    String contextPath = req.getContextPath();
    requestURI = requestURI.replaceAll(contextPath, "").replaceAll("/+", "/");
    if (!HandlerMapping.containsKey(requestURI)) {
      throw new RuntimeException("不存在的url");
    }

    Method method = HandlerMapping.get(requestURI);
    if (method != null) {

      // 获取 method 所在的class
      String simpleName = method.getDeclaringClass().getSimpleName();
      Object o = IOC_NAME.get(simpleName);

      // 请求参数
      Map<String, String[]> parameterMap = req.getParameterMap();
      // 参数动态赋值
      Class<?>[] parameterTypes = method.getParameterTypes();
      Object[] paramValues = new Object[parameterTypes.length];

      for (int i = 0; i < paramValues.length; i++) {
        // 获取 controller 中的参数类型
        Class<?> parameterType = parameterTypes[i];
        if (parameterType.equals(req.getClass())) {
          paramValues[i] = req;
        }
        else if (parameterType.equals(resp.getClass())) {
          paramValues[i] = resp;
        }
        // todo: 2020/7/25 参数内容的设置
        else if (parameterType.equals(String.class)) {
          Annotation[][] parameterAnnotations = method.getParameterAnnotations();
          for (Annotation a : parameterAnnotations[i]) {
            if (a instanceof HFRequestParam) {
              String paramName = ((HFRequestParam) a).name();
              if (!"".equals(paramName.trim())) {
                String value = Arrays.toString(parameterMap.get(paramName))
                    .replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
                paramValues[i] = value;
              }

            }
          }
        }
        else if (parameterType.equals(Integer.class) || parameterType.equals(int.class)) {
          Annotation[][] parameterAnnotations = method.getParameterAnnotations();
          for (Annotation a : parameterAnnotations[i]) {
            if (a instanceof HFRequestParam) {
              String paramName = ((HFRequestParam) a).name();
              if (!"".equals(paramName.trim())) {
                String value = Arrays.toString(parameterMap.get(paramName))
                    .replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
                paramValues[i] = Integer.valueOf(value);
              }

            }
          }
        }
      }
      Object invoke = method.invoke(o, paramValues);
      resp.getWriter().write(invoke.toString());
    }

  }
```