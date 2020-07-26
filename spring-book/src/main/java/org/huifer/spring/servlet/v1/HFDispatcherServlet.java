package org.huifer.spring.servlet.v1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.huifer.spring.annotation.HFAutowired;
import org.huifer.spring.annotation.HFController;
import org.huifer.spring.annotation.HFRequestMapping;
import org.huifer.spring.annotation.HFRequestParam;
import org.huifer.spring.annotation.HFService;

public class HFDispatcherServlet extends HttpServlet {


  public static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";
  public static final String SCAN_KEY = "scanPackage";
  private final Properties SPRING_CONTEXT_CONFIG = new Properties();
  private final List<String> classNameList = new ArrayList<>();
  private final Map<String, Method> HandlerMapping = new HashMap<>();
  /**
   *
   */
  Map<String, Object> IOC_NAME = new HashMap<>();

  @Override
  public void init() throws ServletException {
    // 配置读取
    doLoadConfig();
    // 注解扫描.
    doScan(this.SPRING_CONTEXT_CONFIG.getProperty(SCAN_KEY));
    // 初始化类
    instance();
    // 依赖注入
    autowired();
    // 初始化 handlerMapping
    initHandlerMapping();
    System.out.println();
  }

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

  @Override
  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp) throws ServletException, IOException {
    try {

      dispath(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.getWriter().write(Arrays.toString(e.getStackTrace()));

    }
  }

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

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {

      dispath(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.getWriter().write(Arrays.toString(e.getStackTrace()));

    }
  }
}
