# spring-boot 定时任务 - 邮件
## 定时任务
- 主要注解`@Scheduled`
- 下列代码为一个简单2秒钟间隔持续输出时间的任务
```java
@Component
public class MyTask {

    private static final Logger logger = LogManager.getLogger(MyTask.class);

    @Scheduled(fixedRate = 2 * 1000)
    public void runJobA() {
        logger.info("[定时任务]"
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date()));
    }
}
```
- 日志输出如下，上述代码为一个串行结构。
```log
2019-06-27 14:59:47.520  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:47.520
2019-06-27 14:59:49.521  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:49.521
2019-06-27 14:59:51.521  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:51.521
2019-06-27 14:59:53.521  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:53.521
2019-06-27 14:59:55.520  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:55.520
2019-06-27 14:59:57.520  INFO 17080 --- [   scheduling-1] com.huifer.emailtask.task.MyTask         : [定时任务]2019-06-27 14:59:57.520
```
- 并行任务额外配置项
```java
@Configuration
@EnableScheduling
public class MyTask2 implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(3);
    }
}
```


## 邮箱
- 配置文件添加如下内容
```properties
spring.mail.host=
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth= true
spring.mail.properties.mail.smtp.starttls.enable= true
spring.mail.properties.mail.smtp.starttls.required= true
```
- 简单邮件发送
```java
public class EmailA {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void setMailSender(String to, String subject, String context) throws Exception {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);

        mailSender.send(simpleMailMessage);
    }


}

```
