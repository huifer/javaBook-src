# mybatis-plus 使用
- mybatis-plus 官网 : https://mp.baomidou.com/

## 乐观锁
- 关键字`@Version`
### 乐观锁的作用
- 乐观锁的主要作用是为了解决事务并发带来的问题。相对于悲观锁而言，乐观锁机制采取了更加宽松的加锁机制。

### 乐观锁的实现
- 通过版本号字段 version 进行 , 在 where 条件中补充 version 条件,  
- mybatis-plus 直接通过注解的形式让我们快速使用,不用再sql上自己写version的where条件了

### 使用
```java
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
```

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rbac.demo")
public class Demo implements Serializable {
    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_VERSION = "version";

    public static final String COL_IS_DELETE = "is_delete";

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @Version
    @TableField(value = "version")
    private Long version;

    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;
}
```

## 逻辑删除
- 关键字`@TableLogic`
- 逻辑删除和物理删除的区别,逻辑删除表中还有物理删除表中不存在数据,通常我们使用 is_delete 进行标注

```yaml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
  mapper-locations: classpath:mapper/*Mapper.xml
  typeAliasesPackage: org.huifer.rbac.entity.db
  global-config:
    db-config:
      logic-delete-field: flag  #全局逻辑删除字段值 3.3.0开始支持，详情看下面。
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```



## 测试用例
```java
package org.huifer.rbac.service;

import org.huifer.rbac.entity.db.Demo;
import org.huifer.rbac.mapper.DemoMapper;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    final
    DemoMapper demoMapper;

    public DemoService(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }


    public void lgs() {
        Demo demo = new Demo();
        demo.setName("zs");
        demoMapper.insert(demo);
    }

    public void update() {
        Demo demo = demoMapper.selectById(2L);
        demo.setName("aklsfj");
        demoMapper.updateById(demo);

    }

    public void delete() {
        demoMapper.deleteById(2L);
    }
}

```