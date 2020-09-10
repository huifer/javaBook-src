# 对象比较
## 命题
- 对数据库对象在更新的时候进行数据比较,记录差异.

## 设计

### 确定比较对象
- 在这里使用 Spring 中 `ComponentScan` 的思想.   
    在 Spring 中通过`@Component`注解来说明这是一个组件,在通过`ComponentScan`扫描到带有`@Component`的类进行注册. 




### 确定比较的字段
- 一个数据库对象存在很多字段,可能全部需要比较,也可能只是部分比较.对此需要通过一定的方法找到需要比较的字段.同样使用注解进行控制.
- 在思考一个问题,通常我们使用关系型数据库,会存储的是一个外键(例如:1,2,3),可能不便于阅读. 这里需要将外键转换成可理解的属性.

- 总结：
    1. 单纯值比较
    2. 外键的值比较,可读性



### 实现
- 设计注解 `@HavingDiff` 这个注解的目的是给实体对象使用,用来表示这是一个需要比较的对象

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HavingDiff {

}
```

- 设计注解 `@DiffAnnotation` 这个注解的目的是给字段(属性)使用

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DiffAnnotation {


  /**
   * 字段中文名称
   */
  String name() default "";

  /**
   * 消息,在这里使用[old]和[new]进行替换
   */
  String msg() default "";

  /**
   * mapper class
   */
  Class<?> mapper() default Object.class;

  /**
   * 链接对象
   */
  Class<?> outJoin() default Object.class;


  /**
   * 外联对象需要显示的字符串属性,用来展示的连接字段
   */
  String outField() default "";


}
```


- 注解对应的实体

```java
public class DiffAnnotationEntity {

  String name;

  String msg;

  Class<?> mapper;

  Class<?> outJoin;
  String outField;
}
```


- 注解和注解的实体对象已经设计完成,接下来就需要进行包扫描路径的获取了.

- 使用 Spring 的 enable 类型的开发方式, 写出如下注解.
    - 注解解释
        1. `@Import` 会执行`EnableDiffSelect`中的方法.
        2. `scanPackages` 扫描路径.
        3. `byIdMethod` mapper 的根据id查询方法名.
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {EnableDiffSelect.class})
public @interface EnableDiff {

  String[] scanPackages() default {};

  String byIdMethod() default "selectById";
}

```

- `EnableDiffSelect` 实现 `ImportSelector` 接口,在这里的目的是获取注解`EnableDiff`的两个属性值,放入线程变量,在后续提供扫描入口

```java
public class EnableDiffSelect implements ImportSelector {

  @Override
  public String[] selectImports(
      AnnotationMetadata annotationMetadata) {

    Map<String, Object> annotationAttributes = annotationMetadata
        .getAnnotationAttributes(EnableDiff.class.getName());

    String[] scanPackages = (String[]) annotationAttributes.get("scanPackages");
    String byIdSQL = (String) annotationAttributes.get("byIdMethod");
    DiffThreadLocalHelper.setScan(scanPackages);
    DiffThreadLocalHelper.setByIdMethod(byIdSQL);
    return new String[0];
  }
}
```


- 需要扫描的包路径已经成功获取,接下来就是扫描具有`@HavingDiff`的类
    - 下面代码的思想
        1. 判断这个类是否存有`@HavingDiff`注解
        2. 存在后继续判断字段是否由`@DiffAnnotation`注解
        3. 组装对象放入map对象
            key: 具有`@HavingDiff`注解的类.class
            value: 
                key: 具有`@DiffAnnotation`的字段,类的属性字段
                value: `@DiffAnnotation`的实体对象


```java
@Component
public class DiffRunner implements CommandLineRunner, Ordered {


  /**
   * key: 类的字节码 value: Map -> key: 字段,value: 字段上面的注解对象
   */
  static Map<Class<?>, Map<String, DiffAnnotationEntity>> cache = new HashMap<>();

  public static Map<String, DiffAnnotationEntity> get(Class<?> clazz) {
    return cache.get(clazz);
  }

  @Override
  public void run(String... args) throws Exception {
    List<String> scan = DiffThreadLocalHelper.getScan();

    for (String packageStr : scan) {
      if (!StringUtils.isEmpty(packageStr)) {
        Set<Class<?>> classes = ScanUtils.getClasses(packageStr);
        for (Class<?> aClass : classes) {

          Map<String, DiffAnnotationEntity> diffEntityMap = clazzWork(aClass);
          if (!CollectionUtils.isEmpty(diffEntityMap)) {

            cache.put(aClass, diffEntityMap);
          }
        }
      }
    }
  }

  private Map<String, DiffAnnotationEntity> clazzWork(Class<?> clazz) {
    HavingDiff havingDiff = clazz.getAnnotation(HavingDiff.class);
    // 是否存在这个注解, 如果存在则进行
    Map<String, DiffAnnotationEntity> map = new HashMap<>();
    if (havingDiff != null) {

      for (Field declaredField : clazz.getDeclaredFields()) {
        declaredField.setAccessible(true);
        // 字段名称
        String fieldName = declaredField.getName();
        // 获取注解
        DiffAnnotation diffAnnotation = declaredField.getAnnotation(DiffAnnotation.class);
        if (diffAnnotation != null) {
          DiffAnnotationEntity diffAnnotationEntity = annToEntity(diffAnnotation);
          map.put(fieldName, diffAnnotationEntity);
        }
      }
    }
    return map;
  }


  /**
   * 注解转换成为实体对象
   */
  private DiffAnnotationEntity annToEntity(DiffAnnotation diffAnnotation) {

    DiffAnnotationEntity diffAnnotationEntity = new DiffAnnotationEntity();
    diffAnnotationEntity.setName(diffAnnotation.name());
    diffAnnotationEntity.setMsg(diffAnnotation.msg());
    diffAnnotationEntity.setMapper(diffAnnotation.mapper());
    diffAnnotationEntity.setOutJoin(diffAnnotation.outJoin());
    diffAnnotationEntity.setOutField(diffAnnotation.outField());

    return diffAnnotationEntity;

  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
```


- 比较

1. 比较对象是相同的类
    这里直接通过一个泛型T解决
2. 获取新老字段属性值,比较字段
    反射遍历字段,获取新老字段属性值
3. 通过字段名称从上一步的得到的map中获取注解信息
```text
key: 具有`@HavingDiff`注解的类.class
value: 
    key: 具有`@DiffAnnotation`的字段,类的属性字段
    value: `@DiffAnnotation`的实体对象

```
4. 比较的时候存在前面说到的问题: 外键的可读性.
    注解`@DiffAnnotation`中属性`outField`就是为了解决这个问题而设计.
    - 在可读性之前还需要做一个事情: 查询数据库(根据id查询)得到外联的实体对象
        - 得到实体对象后就可以通过反射来获取属性值了.

5. 差异化信息包装.
```java
public class DiffInfoEntity {

  private String field;
  private String msg;
  private String txId;
  private String ov;
  private String nv;
}
```


```java
@Service
public class IDiffInterfaceImpl<T> implements IDiffInterface<T> {

  private static final String OLD_PLACEHOLDER = "old";
  private static final String NEW_PLACEHOLDER = "new";
  Gson gson = new Gson();
  @Autowired
  private ApplicationContext context;
  @Autowired
  private SqlSession sqlSession;

  /**
   * @param source 原始对象
   * @param target 修改后的对象
   */
  @Override
  public List<DiffInfoEntity> diff(T source, T target, String logTxId) {

    Class<?> sourceClass = source.getClass();
    List<DiffInfoEntity> res = new ArrayList<>();
    for (Field declaredField : sourceClass.getDeclaredFields()) {
      declaredField.setAccessible(true);
      // 字段名称
      String fieldName = declaredField.getName();

      String oldValue = getTargetValue(source, fieldName);
      String newValue = getTargetValue(target, fieldName);

      // 注解对象
      DiffAnnotationEntity fromFiled = getFromFiled(source, fieldName);
      if (fromFiled != null) {

        // 字段中文
        String nameCn = fromFiled.getName();

        // 外联对象的取值字段
        String outField = fromFiled.getOutField();
        // 外联对象的字节码
        Class<?> outJoin = fromFiled.getOutJoin();
        // 外联对象的mapper
        Class<?> mapper = fromFiled.getMapper();

        // 三个值都是默认值则不做外联查询
        if (StringUtils.isEmpty(outField) &&
            outJoin.equals(Object.class) &&
            mapper.equals(Object.class)
        ) {
          if (oldValue.equals(newValue)) {

            String changeLog = changeData(oldValue, newValue, fromFiled.getMsg());
            DiffInfoEntity diffInfoEntity = genDiffInfoEntity(logTxId, nameCn, oldValue, newValue,
                                                              changeLog);
            res.add(diffInfoEntity);

          }
        } else {
          String ov = mapper(mapper, oldValue, outField);
          String nv = mapper(mapper, newValue, outField);
          if (ov.equals(nv)) {

            String changeLog = changeData(ov, nv, fromFiled.getMsg());
            DiffInfoEntity diffInfoEntity = genDiffInfoEntity(logTxId, nameCn, ov, nv, changeLog);
            res.add(diffInfoEntity);
          }
        }
      }
    }
    return res;


  }

  private DiffInfoEntity genDiffInfoEntity(String logTxId, String nameCn, String ov, String nv,
                                           String changeLog) {
    DiffInfoEntity diffInfoEntity = new DiffInfoEntity();
    diffInfoEntity.setField(nameCn);
    diffInfoEntity.setMsg(changeLog);
    diffInfoEntity.setNv(nv);
    diffInfoEntity.setOv(ov);
    diffInfoEntity.setTxId(logTxId);
    return diffInfoEntity;
  }


  private String mapper(Class<?> mapper, Serializable serializable, String filed) {
    try {
      Class<?> aClass = Class.forName(mapper.getName());
      Object mapperObj = Proxy.newProxyInstance(aClass.getClassLoader(),
                                                new Class[]{mapper},
                                                new Target(sqlSession.getMapper(mapper))
      );
      Method selectById = mapperObj.getClass()
          .getMethod(DiffThreadLocalHelper.getIdMethod(), Serializable.class);
      Object invoke = selectById.invoke(mapperObj, serializable);
      return getValue(invoke, filed, "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取变更的文字内容
   */
  private String changeData(String oldValue, String newValue, String msg) {
    return msg.replace(OLD_PLACEHOLDER, oldValue).replace(NEW_PLACEHOLDER, newValue);
  }

  private String getTargetValue(T t, String field) {
    String result = "";
    result = getValue(t, field, result);

    return result;
  }

  private String getValue(Object t, String field, String result) {
    Class<?> aClass = t.getClass();
    for (Field declaredField : aClass.getDeclaredFields()) {

      declaredField.setAccessible(true);

      String fieldName = declaredField.getName();
      if (field.equals(fieldName)) {
        try {
          Object o = declaredField.get(t);
          result = String.valueOf(o);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  /**
   * 根据类型获取注解的实体对象
   * <p>
   * key:字段,value:对象
   *
   * @see DiffAnnotationEntity
   */
  private Map<String, DiffAnnotationEntity> getFromClazz(T t) {
    return DiffRunner.get(t.getClass());
  }

  private DiffAnnotationEntity getFromFiled(T t, String field) {
    return getFromClazz(t).get(field);
  }

  private static class Target implements InvocationHandler {

    private final Object target;

    public Target(Object target) {
      this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      return method.invoke(target, args);
    }
  }


}
```

- 至此全部结束



项目地址: https://github.com/huifer/crud
分支: dev