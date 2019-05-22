# Spring-mvc
## thymeleaf

### 配置

- Properties配置内容

```properties
# THYMELEAF (ThymeleafAutoConfiguration)
spring.thymeleaf.cache=true # Whether to enable template caching.
spring.thymeleaf.check-template=true # Whether to check that the template exists before rendering it.
spring.thymeleaf.check-template-location=true # Whether to check that the templates location exists.
spring.thymeleaf.enabled=true # Whether to enable Thymeleaf view resolution for Web frameworks.
spring.thymeleaf.enable-spring-el-compiler=false # Enable the SpringEL compiler in SpringEL expressions.
spring.thymeleaf.encoding=UTF-8 # Template files encoding.
spring.thymeleaf.excluded-view-names= # Comma-separated list of view names (patterns allowed) that should be excluded from resolution.
spring.thymeleaf.mode=HTML # Template mode to be applied to templates. See also Thymeleaf's TemplateMode enum.
spring.thymeleaf.prefix=classpath:/templates/ # Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.reactive.chunked-mode-view-names= # Comma-separated list of view names (patterns allowed) that should be the only ones executed in CHUNKED mode when a max chunk size is set.
spring.thymeleaf.reactive.full-mode-view-names= # Comma-separated list of view names (patterns allowed) that should be executed in FULL mode even if a max chunk size is set.
spring.thymeleaf.reactive.max-chunk-size=0B # Maximum size of data buffers used for writing to the response.
spring.thymeleaf.reactive.media-types= # Media types supported by the view technology.
spring.thymeleaf.render-hidden-markers-before-checkboxes=false # Whether hidden form inputs acting as markers for checkboxes should be rendered before the checkbox element itself.
spring.thymeleaf.servlet.content-type=text/html # Content-Type value written to HTTP responses.
spring.thymeleaf.servlet.produce-partial-output-while-processing=true # Whether Thymeleaf should start writing partial output as soon as possible or buffer until template processing is finished.
spring.thymeleaf.suffix=.html # Suffix that gets appended to view names when building a URL.
spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain.
spring.thymeleaf.view-names= # Comma-separated list of view names (patterns allowed) that can be resolved.
```

- `org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties` 配置内容的对应类

  ```java
  @ConfigurationProperties(prefix = "spring.thymeleaf")
  public class ThymeleafProperties {
  
     private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
  
     public static final String DEFAULT_PREFIX = "classpath:/templates/";
  
     public static final String DEFAULT_SUFFIX = ".html";
      ...
  }
  ```

- 通过  `@ConfigurationProperties(prefix = "spring.thymeleaf")` 前缀找到对应的 配置属性

- 模板寻址

  - prefix-view-name+suffix 

    ```java
    	public static final String DEFAULT_PREFIX = "classpath:/templates/";
    	public static final String DEFAULT_SUFFIX = ".html";
    ```

    ```java
    @Controller
    public class Tcontroller {
    
        @GetMapping("/")
        public String hello(Model model) {
            model.addAttribute("message", "hello");
            return "h1";
        }
    
    }
    ```

    默认情况下 地址为  classpath:/templates/ + hello方法的返回值 + ".html"  

    - /target/classes/templates/h1.html





### 渲染

### el表达式

- 逻辑表达式
- 迭代表达式
