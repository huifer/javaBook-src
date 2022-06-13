# Crud 项目介绍
> 简化单表的CRUD基本代码.



## 项目地址

- https://github.com/huifer/crud/tree/dev



## 为什么使用
- 比如学生管理系统.表设计有 课程表`t_classes`等等...在管理系统中我们需要添加`课程`的时候需要做一次 controller 、 service 、 redis 、 dao 这几类操作. 每多一个表格都需要做这一批操作.
    一般有新增、修改、删除、根据 id 查询. 
    
    - 当使用了这个项目后通过标记一些注解即可获得上述的功能.

假设现在有表格



| 字段   |类型      |
| ---- | ---- |
|  id    |    int   |
|   name   | varchar     |



## 常规实现

1. 创建  controller 类
2. 创建 service 类
3. 创建 一些验证方法
4. 组合起来

```java
@RestController
@RequestMapping("/demo")
public class ProjectDemoController {

  @Autowired
  private ProjectDemoMapper projectDemoMapper;

  @PostMapping("/add")
  public ResultVO add(
      @RequestBody ProjectDemo req
  ) {
    int i = projectDemoMapper.insertSelective(req);
    if (i > 0) {
      return ResultVO.success();
    }
    else {
      return ResultVO.failed();
    }
  }
}

```

- 省略验证方法, 省略service 编写. 正常应该分一分. 

- 现在 只有数据库层面的交互代码我们可能还需要一个redis上的操作

  (hash数据类型) 那么这部分代码还需要在写一次

  ```java
  @RestController
  @RequestMapping("/demo")
  public class ProjectDemoController {
  
    @Autowired
    private ProjectDemoMapper projectDemoMapper;
  
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
  
    @PostMapping("/add")
    public ResultVO add(
        @RequestBody ProjectDemo req
    ) {
      int i = projectDemoMapper.insertSelective(req);
      if (i > 0) {
        stringRedisTemplate.opsForHash()
            .put("demo", String.valueOf(req.getId()), JSON.toJSONString(req));
        return ResultVO.success();
      }
      else {
        return ResultVO.failed();
      }
    }
  }
  ```

  - 这部分代码出现的次数可能会随着实体类的增多而增多. 每当一个表出现上述的 CRUD 都需要在编辑以便代码. 很是繁琐  ， 对此提出了一个设想. 






## crud 实现
### 省略 controller 的编写

- 路径说明: `/rest`+ `@CrudController#uri` + `add\editor\del\byId`

- 定义一个 实体对象



```java
@CrudController(uri = "/project/demo", idType = Integer.class)
public class ProjectInt extends AbsEntity implements Serializable {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
```

- 定义一个mapper

  ```java
  @Mapper
  @CacheKey(type = ProjectInt.class,key = "asdc")
  public interface ProjectIntMapper extends A<Integer, ProjectInt> {
  
    @Override
    @Insert("INSERT INTO `project_int`(`name`) VALUES (#{name} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(ProjectInt record);
  
    @Override
    @Select("select * from project_int where id = #{id,javaType=INTEGER} ")
    ProjectInt selectByPrimaryKey(@Param("id") Integer integer);
  
    @Override
    @Delete("DELETE FROM `dest`.`project_int` WHERE `id` = #{id} ")
    int deleteByPrimaryKey(@Param("id") Integer integer);
  
    @Override
    @Update("UPDATE `dest`.`project_int` SET `name` = #{name}  WHERE `id`= #{id}  ")
    int updateByPrimaryKeySelective(ProjectInt record);
  }
  ```



- 注册 servlet

  ```java
  @Bean
  ServletRegistrationBean myServletRegistration() {
    ServletRegistrationBean srb = new ServletRegistrationBean();
    srb.setServlet(new OcaServlet());
    srb.setUrlMappings(Arrays.asList("/rest/*"));
    return srb;
  }
  ```



- 权限验证:**在使用了两个servlet后通过拦截器验证会有问题，请使用 filter 进行权限验证**





#### 自定义入参验证

- 自定义参数验证是不可避免的. 在这里提出一个接口`com.github.huifer.crud.ctr.validated.ValidatedInterface`
- `entityClass` 填写对应的数据库对象即可

```java
@Service
public class ProjectIntValidated implements ValidatedInterface<ProjectInt> {

  Gson gson = new Gson();


  public Class<?> entityClass() {
    return ProjectInt.class;
  }

  public void validateDelete(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateAdd(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateById(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }

  public void validateEditor(ProjectInt projectInt) {
    System.out.println(gson.toJson(projectInt));
  }
}
```



- 如果验证不通过可以在这个方法中直接抛出异常. 



### 省略 mybatis + redis 编写

- 有时我们需要直接调用普通的 crud 不需要从 controller 进行调用 。在这个前提下我们可以使用

- 需要在mapper 和 db 实体类进行标记

```java

public class IssuesEntity implements BaseEntity {


  private Integer id;
  private String newTitle;
  private Date date;
 
}

@Mapper
@CacheKey(key = "issues", type = IssuesEntity.class)
public interface IssuesMapper extends A<Integer, IssuesEntity> {

  @Insert("   insert into issue(new_title)values(#{newTitle,jdbcType=VARCHAR})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insertSelective(IssuesEntity record);

  @Select("select id as id , new_title as newTitle from issue where id = #{integer} ")
  IssuesEntity selectByPrimaryKey(Integer integer);

  @Override
  @Update("UPDATE `issue` SET `new_title` = #{newTitle}  WHERE `id` = #{id} ")
  int updateByPrimaryKeySelective(IssuesEntity record);

  @Override
  @Delete("delete from issue where id = #{integer}")
  int deleteByPrimaryKey(Integer integer);
}
```

- 调用层代码

```java
  @Autowired
  private CrudFacade<IssuesEntity, IntIdInterface<Integer>> crudFacade;


 @Test
  void testInsert() {
    IssuesEntity issuesEntity = new IssuesEntity();
    issuesEntity.setNewTitle("mybatis_test");
    crudFacade.insert(issuesEntity);
  }

```



- 使用后直接具有 db 操作和 redis 的操作




### 省略 redis 编写

- 有时对象可能直接存储在 redis 中而不是存储 db 。 在这个前提下可以使用



- 类标记

```
@CacheKey(key = "tt", type = IssuesEntity.class, idMethod = "ooo")
public class IssuesEntity implements BaseEntity {


  private Integer id;
  private String newTitle;
  private Date date;
}
```



- 调用层代码

```java
@Autowired
private CrudEntityFacade<IssuesEntity> crudEntityFacade;

@Test
void testInsert() {
  IssuesEntity issuesEntity = new IssuesEntity();
  issuesEntity.setNewTitle("insert");
  issuesEntity.setDate(new Date());
  crudEntityFacade.insert(issuesEntity);
}
```



### 自定义 json 序列化规则

- 目前只支持 gson 和 jackjson 两种
  - gson 自定实现: `com.github.huifer.crud.common.conf.json.GsonConfigSetting`
  - jackjson 自定义实现: `com.github.huifer.crud.common.conf.json.JackJsonConfigSetting`





- 具体用例请查看: https://github.com/huifer/crud/tree/dev/simple-example





## 注意

**目前 redis 仅支持 hash 操作**

**欢迎各位提出意见 : https://github.com/huifer/crud/issues**