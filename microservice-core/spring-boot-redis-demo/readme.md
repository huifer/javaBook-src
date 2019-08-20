# spring-boot redis
- 依赖
```xml
  <dependencies>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>2.9.0</version>
    </dependency>

  </dependencies>
```

- 配置文件
```properties
spring.redis.host=localhost
spring.redis.database=0
spring.redis.port=6379
spring.redis.timeout=1000




spring.redis.1.host=localhost
spring.redis.1.database=1
spring.redis.1.port=6379
spring.redis.1.timeout=1000

```
- 本文实例：创建2个redisTemplate 写入Pojo实体。
- 既然需要写入实体那么必然需要序列化
```java
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializeConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializeConverter = new DeserializingConverter();

    @Override
    public byte[] serialize(Object o) throws SerializationException {

        if (o == null) {
            return new byte[0];
        }

        return this.serializeConverter.convert(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return this.deserializeConverter.convert(bytes);
    }

}

```
  
  
- 实体对象
```java
public class Student implements Serializable {


    private static final long serialVersionUID = 4128119411324040794L;
    private String name;
    private Integer age;
    private String clazz = Student.class.getName();

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName()) &&
                Objects.equals(getAge(), student.getAge()) &&
                Objects.equals(getClazz(), student.getClazz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getClazz());
    }
}
```

- redisTemplate创建
1. 自动装配
```java
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer());

        return redisTemplate;
    }
```
2. 手动装配
由于方法过期`setHostName` ,`setPort`等几个方法过期所以不推荐使用下列方法
```java
        JedisConnectionFactory f = new JedisConnectionFactory();
        
        f.setHostName();
        // ...
```

推荐方法
```java

 public RedisConnectionFactory factory(
            String host,
            int database,
            int port,
            int timeout
    ) {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(timeout);

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
                        .builder();
        jpcb.poolConfig(poolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean(name = "redis_2")
    public RedisTemplate<String, Object> stringObjectRedisTemplate(
            @Value("${spring.redis.1.host}") String host,
            @Value("${spring.redis.1.database}") int database,
            @Value("${spring.redis.1.port}") int port,
            @Value("${spring.redis.1.timeout}") int timeout
    ) {
        RedisConnectionFactory factory = factory(host, database, port, timeout);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer());
        return redisTemplate;
    }
```


- 测试
```java

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier(value = "redis_2")
    private RedisTemplate<String, Object> r2;

    @Test
    public void testSet() {
        Student student = new Student();
        student.setAge(10);
        student.setName("zhangsan");

        redisTemplate.opsForValue().set("student-02", student);

        Student s = new Student();
        Object o = redisTemplate.opsForValue().get("student-02");
        BeanUtils.copyProperties(o, s);
        Assert.assertTrue(s.equals(student));

    }


    @Test
    public void testSet2() {
        Student student = new Student();
        student.setAge(10);
        student.setName("wangwu");

        r2.opsForValue().set("student-03", student);

        Student s = new Student();
        Object o = r2.opsForValue().get("student-03");
        BeanUtils.copyProperties(o, s);
        Assert.assertTrue(s.equals(student));

    }


}

```


## StringRedisTemplate
> 常用的一些操作对象
> - 操作字符串 redisTemplate.opsForValue();　　 
> - 操作hash redisTemplate.opsForHash();　　  
> - 操作list redisTemplate.opsForList();　　  


### stringRedisTemplate 操作代码如下
```java
  /**
     * 设置k-v
     */
    public void setString(String k, String v) {
        stringRedisTemplate.opsForValue().set(k, v);
    }

    /**
     * 读取k下面的v
     *
     * @param k
     * @return
     */
    public String getString(String k) {
        return stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * 设置list
     */
    public void setList(String k, String v) {
        stringRedisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 删除同一个元素v
     */
    public void removeAllElement(String k, String v) {
        stringRedisTemplate.opsForList().remove(k, 0, v);
    }

    /**
     * 获取k
     */
    public List<String> getList(String k) {
        return stringRedisTemplate.opsForList().range(k, 0, -1);
    }

    /**
     * 设置hash
     * {bigK:[{k:v},{k:v}]}
     *
     * @param bigK 最外层k
     * @param k    内层k
     * @param v    内层v
     */
    public void setHash(String bigK, String k, String v) {
        stringRedisTemplate.opsForHash().put(bigK, k, v);
    }

    /**
     * 获取hash
     *
     * @param bigK
     * @return
     */
    public Map<String, String> getHash(String bigK) {
        return stringRedisTemplate.<String, String>opsForHash().entries(bigK);
    }

    /**
     * 删除内层k
     *
     * @param bigK
     * @param k
     */
    public void removeK(String bigK, String k) {
        stringRedisTemplate.opsForHash().delete(bigK, k);
    }

```

有了这些基本的操作方法我们还需要进行对Bean 实体进行操作,这里使用的是[fastjson](https://github.com/alibaba/fastjson)继续bean转换成json文本(互相转换)
> 具体操作对象有
> - bean
> - list
> - map

### fastjson 转换工具类
```java
package com.huifer.redis.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.*;
import java.util.stream.Collectors;

public class FastJsonUtils {

    private FastJsonUtils() {
        throw new RuntimeException("this is a utils");
    }

    /**
     * bean to jsonStr
     *
     * @param bean bean
     */
    public static <T> String toJsonStr(T bean) {
        return JSON.toJSONString(bean);
    }

    /**
     * jsonStr to bean
     *
     * @param jsonStr jsonStr
     * @param clazz   clazz
     * @return bean
     */
    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * map to jsonStr
     *
     * @param map map
     * @return jsonStr
     */
    public static <K, V> String toBean(Map<K, V> map) {
        return JSON.toJSONString(map);
    }

    /**
     * list to jsonStr
     *
     * @param list list
     * @return jsonStr
     */
    public static <T> String toJsonStr(List<T> list) {
        return JSON.toJSONString(list);
    }

    /**
     * jsonStr to map
     *
     * @param jsonStr jsonStr
     * @param map     map
     * @return {@link Map}
     */
    public static <K, V> Map<K, V> strToMap(String jsonStr, Map<K, V> map) {
        Map<K, V> kvMap = JSON.parseObject(jsonStr, new TypeReference<Map<K, V>>() {
        });
        Map<K, V> result = new HashMap<>();
        Class<?> aClass = map.values().stream().collect(Collectors.toList()).get(0).getClass();
        Map<K, V> m = new HashMap<>();
        kvMap.forEach(
                (k, v) -> {
                    JSONObject jsonObject = (JSONObject) v;
                    V o = (V) jsonObject.toJavaObject(aClass);

                    m.put(k, o);
                }
        );

        return m;
    }

    /**
     * jsonStr to list
     *
     * @param jsonStr jsonStr
     * @param bean   bean
     * @return {@link List}
     */
    public static <T> List<T> strToList(String jsonStr, T bean) {
        List<JSONObject> list = JSON.parseObject(jsonStr, new TypeReference<List<JSONObject>>() {

        });
        List<T> res = new ArrayList<>();
        for (JSONObject jsonObject : list) {
            T o = (T) jsonObject.toJavaObject(bean.getClass());
            res.add(o);
        }
        return res;
    }


}

```

这样就可以对stringRedisTemplate进行改进
```java
 /**
     * 设置k , value 为 bean
     *
     * @param k    k
     * @param bean bean对象
     */
    public <T> void setStringBean(String k, T bean) {
        this.setString(k, FastJsonUtils.toJsonStr(bean));
    }

    /**
     * 获取对象
     *
     * @param k     k
     * @param clazz clazz
     */
    public <T> T getStringBean(String k, Class<T> clazz) {
        String string = this.getString(k);
        return FastJsonUtils.toBean(string, clazz);
    }

    public <T> void setList(String k, List<T> list) {
        this.setString(k, FastJsonUtils.toJsonStr(list));
    }

    public <T> List<T> getList(String k, T bean) {
        String string = this.getString(k);
        return FastJsonUtils.strToList(string, bean);
    }

    public <K, V> void setHash(String k, Map<K, V> map) {
        this.setString(k, FastJsonUtils.toBean(map));
    }

    public <K, V> Map<K, V> getHash(String k, Map<K, V> map) {
        String string = this.getString(k);
        return FastJsonUtils.strToMap(string, map);
    }

```