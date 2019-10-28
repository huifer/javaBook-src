# spring 注解
- Author: [HuiFer](https://github.com/huifer)
## 核心注解
### @Required
- 在Bean的set方法上使用,表示该属性不可为空
### @Autowired
- 自动注入Bean,方式为ByType。使用`@Autowired(required = false)`时,即便找不到Bean也不会报错
### @Qualifier
- 该注解通常和`@Autowired`一起使用,可以当作ByName注入Bean,使用`@Qualifier`可以避免相同类型不同作用Bean注入时的混乱
### @Configuration
- 等价于spring.xml配置文件,该注解可以替换为JavaBean方式进行开发，如需使用xml方式开发可以使用`@ImportResource(locations={"classpath:applicationContext.xml"})`方式.
### @ComponentScan
- 指定bean扫描路径，和`@Configuration`一起使用来发现和装配Bean.如果不填写`basePackages`会从该注解所在包以及子包开始扫描。
### @ImportResource
- 用来加载xml配置文件

### @Bean
- 等价于`·`<bean/>`,将注解放在方法上交给spring管理
### @Lazy
- 延时加载.与`@Configuration`一起使用所有的Bean都是懒加载.
### @Value
- 用于注入`application.yml`或`application.properties`中配置的属性值
### @Import
- 导入其他配置类

## 语义注解
### @Component
- 类级别，表示这是一个spring组件.
### @Controller
- 类级别，定义controller,修饰url请求层组件。其本质也是`@Commponent`
### @RestController
- 类级别，定义controller,修饰url请求层组件。其本质也是`@Commponent`
### @Service
- 类级别，定义service,修饰service层组件.其本质也是`@Commponent`
### @Repository
- 类级别，定义repository,修饰dao层组件。其本质也是`@Commponent`

## MVC注解
### @Controller
- controller层组件
### @RestController
- controller层组件
### @RequestMapping
- 表示url请求地址
    - GetMapping：`@RequestMapping(method = RequestMethod.GET)`
    - PostMapping：`@RequestMapping(method = RequestMethod.POST)`
    - PutMapping：`@RequestMapping(method = RequestMethod.Put)`
    - PatchMapping：`@RequestMapping(method = RequestMethod.PATCH)`
    - DeleteMapping：`@RequestMapping(method = RequestMethod.DELETE)`
### @CookieValue
- 用在方法上获取cookie
### @CrossOrigin
- 跨域实现
### @ExceptionHandler
- 用在方法上，指定具体异常处理类
### @InitBinder
- 初始化数据绑定器，用于数据绑定，数据转换
### @Valid
- 数据验证
### @MatrixVariable
- 矩阵变量
### @PathVariable
- 路径变量
### @RequestAttribute
- 绑定请求属性到handler方法参数
### @RequestBody
- 指示方法参数应该绑定到Http请求Body HttpMessageConveter负责将HTTP请求消息转为对象
### @RequestHeader
- 映射控制器参数到请求头的值
### @RequestParam
- 请求参数
### @ResponseBody
- 返回值对象注解转换成json
### @ResponseStatus
- 返回状态码
### @SessionAttribute
- 用于方法参数。绑定方法参数到会话属性
### @SessionAttributes
- 用于将会话属性用Bean封装
### @ModelAttribute
- 把值绑定到Model中，使全局@RequestMapping可以获取到该值



## 定时任务注解
### @Scheduled
- 方法级别，参数为 cron 表达式

## 调度注解
### @Async
- 方法级别,每个方法均都在单独的线程中，可接受参数；可返回值，也可不返回值。


## 测试注解
### @BootstrapWith
- 类级别，配置spring测试上下文
### @ContextConfiguration
- 类级别，指定配置文件
### @WebAppConfiguration
- 类级别,指定测试环境 WebAppConfiguration
### @Timed
- 方法级别,指定测试方法的执行时间,超时失败
### @Repeat
- 方法级别,指定运行次数
### @Commit
- 类级别&方法级别,指定事务提交
### @RollBack
- 类级别&方法级别，指定事务的回滚
### @DirtiesContext
### @BeforeTransaction
- 方法级别,指示具有该注解的方法应该在所有`@Transactional`注解的方法之前执行
### @AfterTransaction
- 方法级别,指示具有该注解的方法应该在所有`@Transactional`注解的方法之后执行
### @Sql
- 类级别&方法级别，运行Sql脚本 方法上的@Sql会覆盖类级别的@Sql
### @SpringBootTest
- spring boot 集成测试上下文
### @DataJpaTest
### @DataMongoTest
- 内置MongoDB集成测试
### @WebMVCTest
- controller层测试
### @AutoConfigureMockMVC
- controller层测试,加载springboot完整上下文
### @MockBean
- 注入MockBean
### @JsonTest
### @TestPropertySource

## JPA注解
### @Entity
- 实体类注解，常和`@Table`配合使用
### @Table
- 表注解,`name`填写数据库表名
### @Column
- 数据表列注解
### @Id
- 主键注解
### @GeneratedValue
- 主键生成策略
    - TABLE:使用一个特定的数据库表格来保存主键。
    - SEQUENCE:根据底层数据库的序列来生成主键，条件是数据库支持序列。
    - IDENTITY:主键由数据库自动生成（主要是自动增长型）
    - AUTO:主键由程序控制
### @SequenceGeneretor
- 自动为实体的数字标识字段/属性分配值
### @MappedSuperClass
- 用在确定是父类的Entity上。父类的属性可被子类继承
### @NoRepositoryBean
- 在充当父类的Repository上注解，告诉Spring不要实例化该Repository
### @Transient
- 表示该属性并非是一个到数据库表字段的映射,ORM框架应忽略它。 如果一个属性并非数据库表的字段映射,就务必将其标示为`@Transient`,否则,ORM框架默认其注解为`@Basic`。
### @Basic
- 指定实体数据属性的加载方式
    - LAZY：延迟加载
    - EAGER：全部加载
### @JsonIgnore
- 指示当进行序列化或反序列化时，忽略该属性
### @JoinColumn
- 一对一：本表中指向另一个表的外键。 一对多：另一个表指向本表的外键
### @OneToOne
- 一对一注解
### @OneToMany
- 一对多注解
### @ManyToOne
- 多对一注解

## 事务注解
### @Transactional
- 用于接口、接口中的方法、类、类中的公有方法 光靠该注解并不足以实现事务
  仅是一个元数据，运行时架构会使用它配置具有事务行为的Bean
- 支持特性
    - 传播类型
    - 隔离级别
    - 操作超时
    - 只读标记

## 缓存注解
### @Cacheable
- 方法级别
### @CachePut
- 方法级别，更新缓存
### @CacheEvict
- 方法级别，清除缓存
### @CacheConfig
- 类级别，缓存配置

## SpringBoot注解
### @EnableAutoConfiguration
- 自动配置注解,可以将该注解配合`@Configuration`来完成配置类的自动装配
### @SpringBootApplication
- 该注解是以下三个注解的合并体`@SpringBootConfiguration`、`EnableAutoConfiguration`和`@ComponentScan`

## Spring Cloud注解
### @EnableConfigServer
- 配置服务器
### @EnableEurekaServer
- 服务注册
### @EnableDiscoveryClient
- 服务发现
### @EnableCircuitBreaker
- 熔断器
### @HystrixCommand
- 服务降级


## 异常注解
### @ExceptionHandler
- 捕获异常进行处理
### @ControllerAdvice
-  统一异常处理，一般与`@ExceptionHandler`一起使用
### @RestControllerAdvice
- @RestControllerAdvice= @ControllerAdvice +@ResponseBody