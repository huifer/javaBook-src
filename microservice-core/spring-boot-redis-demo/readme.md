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
