# 从零开始编写redis可视化项目

- Author : [HuiFer](https://github.com/huifer)

## idea
- redis 可视化软件有很多, 大多都是独立安装后进行使用. 
    通常在 SpringBoot 项目中配置了 redis 还需要打开一个软件去使用, 笔者觉得有一丝不便, 为什么不是直接访问一个地址直接可以操作呢? 抱着这样的想法开始了下面的故事. (有踩一捧一的嫌疑, 其实在开发的时候并没有这个想法,就只是想做一个东西.)


想法有了接下来就是如何实现了. 先要确定项目支持那些操作. 项目是对 redis 可视化,必然少不了对键值的操作(string,set,zset,list,hash),以及 redis 信息查询. 
需求明确接下来就是开发了. 


## coding 
- 开始前需要做好技术选型. 我们使用什么来操作 redis? 前文说到项目是希望在SpringBoot中集成,那这里必然选择了下面这个依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

- 接下来就是对 redis 操作上的编写了. 笔者会挑选几个处理上存在一定难度的贴出代码供各位进行一个学习. 

### KEY 搜索
- 对 redis 中某一个 db 进行 key 搜索, 需要调用 `org.springframework.data.redis.connection.RedisKeyCommands.keys`
- 接口确认后,需要决定返回值. 为了便于后续操作这里笔者需要如下几个信息
    1. 过期时间
    2. 键值类型
    3. key name
    
    

代码如下

```java
	@Override
	public List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		RedisConnection connection =
				RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());

		// 通过 key region 进行搜索
		Set<byte[]> keys = connection.keys(keyRegion.getBytes());
		StringRedisSerializer stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);

		List<RedisKeyInfo> result = new ArrayList<>();

		// 数据封装
		for (byte[] key : keys) {
			String keyName = stringSerializer.deserialize(key);

			Long expire = redisTemplate.getExpire(keyName);

			DataType type = connection.type(key);
			RedisDataType redisDataType = RedisDataType.valueOf(type.name());
			RedisKeyInfo row = new RedisKeyInfo(keyName, redisDataType, expire);
			// 从缓存中拿出key的value
			result.add(row);
		}

		return result;
	}

```



### list 删除某一个元素
- 先给 某一行换名字再删掉这个key,之所以需要用uuid怕重名.
```java
	@Override
	public void removeByRow(RedisConnectionConfig config, String k, int row) {
		ListOperations listOperations = factory.factory(config).opsForList();
		UUID uuid = UUID.randomUUID();
		listOperations.set(k, row, "__delete__" + uuid.toString());
		listOperations.remove(k, 0, "__delete__" + uuid.toString());
	}
```


### ZSET 数据添加
- 添加 zset 时主要需要处理插入对象, 这里需要使用`Set<ZSetOperations.TypedTuple<Object>>`对象进行写入

```java
@Override
	public void add(RedisConnectionConfig config, String k, double score, String member) {
		Set<ZSetOperations.TypedTuple<Object>> zset = new HashSet<>();
		zset.add(
				new ZSetOperations.TypedTuple<Object>() {
					private final String data;

					private final double sc;

					@Override
					public Object getValue() {
						return data;
					}

					@Override
					public Double getScore() {
						return sc;
					}

					@Override
					public int compareTo(ZSetOperations.TypedTuple<Object> o) {
						if (o == null) {
							return 1;
						}
						return this.getScore() - o.getScore() >= 0 ? 1 : -1;
					}

					{
						sc = score;
						data = member;
					}
				});

		factory.factory(config).opsForZSet().add(k, zset);
	}
```



### redis 服务信息
- 其他的键值操作就请各位自行调用 redisTemplate 中提供的操作接口了. 在完成键值操作后需要做 redis 服务信息相关的接口
- redis 服务信息命令
    - memory
    - clients
    - server
    - 其他

- 主要调用`org.springframework.data.redis.connection.RedisServerCommands.info(java.lang.String)`,返回的是 `Properties` 对象, 在一行行读取信息即可得到命令返回值.




- 至此数据信息和键值操作都已经完成. 接下来就是处理对外接口了. 


### servlet 编写

- 这里参考自: druid 的servlet编写(读取文件方法). 

- servlet 需要面对的问题
    1. 登录验证(安全性). 
    2. 请求方式.请求参数
        - 采用 get 和 post 请求




#### 账号密码
- 这里通过 ServletRegistrationBean 将初始化参数设置 , 详细代码如下

```java
    @Bean
	public ServletRegistrationBean viewRedisServlet() {
		ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
		servletServletRegistrationBean.setServlet(new ViewRedisServlet("/support/"));
		Map<String, String> initParams = new HashMap<>(10);
		ViewRedisConfig bean = context.getBean(ViewRedisConfig.class);


		if (bean != null && !bean.equals(new ViewRedisConfig())) {

			initParams.put(LOGIN_NAME_PARAM, bean.getLoginName());
			initParams.put(PASSWORD_PARAM, bean.getPassword());

		}
		servletServletRegistrationBean.setInitParameters(initParams);
		servletServletRegistrationBean.setUrlMappings(Collections.singleton("/redis/*"));
		return servletServletRegistrationBean;
	}
```


- 接下来就是修改 servlet 的init方法,读取初始化参数,设置给成员变量.



##### 登录逻辑

- 输入账号密码判断是否和初始化相同


```java

		if (path.equals("/login")) {
			String loginNameParam = req.getParameter(LOGIN_NAME_PARAM);
			String paramPassword = req.getParameter(PASSWORD_PARAM);


			if (this.loginName.equals(loginNameParam) && this.password.equals(paramPassword)) {
				req.getSession().setAttribute(LOGIN_NAME_PARAM, loginNameParam);
				resp.getWriter().print("success");
			}
			else {
				resp.getWriter().print("error");
			}

		}

		// 进入登录页面
		if ("".equals(path) || "login".equals(path) || "/".equals(path)) {
			returnResourceFile("login.html", "", resp);
		}

```


#### 返回静态页面
- 详情代码可以参考: [Druid-Utils](https://github.com/alibaba/druid/blob/ae15e0c590a768c9b10bddbfe3a50643401a4754/src/main/java/com/alibaba/druid/util/Utils.java)

```java
	protected void returnResourceFile(
			String fileName, String uri, HttpServletResponse resp
	) throws IOException {


		String filePath = getFilePath(fileName);

		if (filePath.endsWith(".html")) {
			resp.setContentType("text/html; charset=utf-8");

		}

		String text = MyUtils.readFromResource(filePath);
		assert text != null;
		resp.getWriter().write(text);

	}
```

#### 对外接口开发

- 对外接口开发这里采用get和post请求

  get 请求不传递参数

  post 请求传递 body json参数



根据上述进行具体开发即可. 这里代码就不贴出来了.







### Spring Boot 嵌入



至此servlet完成开发 . 接下来就是处理如何接入SpringBoot 做到嵌入.

需要处理的问题

1. 如何嵌入
2. 如何读取 redis 配置



#### 嵌入



##### 账号密码自定义配置

- 提供 application.yml 配置时候的提醒文件





```java
{
  "properties": [
    {
      "name": "view.redis.login_name",
      "type": "java.lang.String",
      "description": "登录的账号",
      "defaultValue": "redis-admin",
      "sourceType": "com.github.huifer.view.redis.servlet.ViewRedisConfig"
    },
    {
      "name": "view.redis.password",
      "type": "java.lang.String",
      "defaultValue": "redis-admin",
      "description": "登录的密码",
      "sourceType": "com.github.huifer.view.redis.servlet.ViewRedisConfig"
    }
  ]

}
```

根据提醒文件创建出对应对象.具体对象不贴出了. 



- 配置 servlet 的账号密码. 
  - 前文有`ViewRedisConfig`用来存储账号密码. 需要将账号密码写入 servlet , 详细代码如下

```java
@Component
@ComponentScan("com.github.huifer.view.redis.*")
@EnableConfigurationProperties(ViewRedisConfig.class)
public class Beans {

   @Autowired
   private ApplicationContext context;

   @Bean
   public ServletRegistrationBean viewRedisServlet() {
      ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
      servletServletRegistrationBean.setServlet(new ViewRedisServlet("/support/"));
      Map<String, String> initParams = new HashMap<>(10);
      ViewRedisConfig bean = context.getBean(ViewRedisConfig.class);

	  // 写入账号密码
      if (bean != null && !bean.equals(new ViewRedisConfig())) {

         initParams.put(LOGIN_NAME_PARAM, bean.getLoginName());
         initParams.put(PASSWORD_PARAM, bean.getPassword());

      }
      servletServletRegistrationBean.setInitParameters(initParams);
      servletServletRegistrationBean.setUrlMappings(Collections.singleton("/redis/*"));
      return servletServletRegistrationBean;
   }
}
```







##### redis 配置读取

- 配置读取采用的方式是 判断 bean 是否存在

- 通过 `@ConditionalOnMissingBean({RedisProperties.class, RedisConnectionFactory.class})` 来判断是否可以执行runner 代码,即 读取spring boot 配置文件的redis配置

```java
@Component
public class ViewRedisServletRunner {

   private static final Logger log = LoggerFactory.getLogger(ViewRedisServletRunner.class);

   @Autowired
   private ApplicationContext context;

   @Bean
    // 判断是否有 RedisProperties 和 RedisConnectionFactory bean 只有存在才会执行代码
   @ConditionalOnMissingBean({RedisProperties.class, RedisConnectionFactory.class})
   public ApplicationRunner runner() {
      return args -> {
         RedisProperties redisProperties = context.getBean(RedisProperties.class);

         if (log.isDebugEnabled()) {
            log.debug("开始设置 RedisProperties");

         }

         RedisConnectionFactory redisConnectionFactory = context.getBean(RedisConnectionFactory.class);


         SpringRedisProperties springRedisProperties = new SpringRedisProperties();
         springRedisProperties.setProperties(redisProperties);
         springRedisProperties.setRedisConnectionFactory(redisConnectionFactory);

         SingletData.setSpringRedisProperties(springRedisProperties);

      };
   }
}
```





##### 对外注解

- 通过下面接口进行对外使用

  具体作用就是将 beans 这个类加载到spring容器

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {Beans.class})
public @interface EnableViewRedisServlet {
}
```

