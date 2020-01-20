
## 依赖管理
1. 项目初始化阶段导入基本依赖
2. 项目进行时添加依赖
    - 直接添加依赖会使得整个项目的体积变大,比如`apache common-lang3`可能使用到的只有一两个方法,此时请手动编写这些方法
    - 依赖冲突问题,导入依赖后需要对原有代码进行测试是否会存在影响,如下
- 异常版本
```xml
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.61</version>
        </dependency>
```
- 正常版本
```xml
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.60</version>
        </dependency>
```
- 这两个版本乍一看是一个升级但是在使用`com.alibaba.fastjson.serializer.ValueFilter`时出现了异常,版本升级带来了问题. https://github.com/alibaba/fastjson/issues/2780
3. 版本升级谨慎进行

## bean 验证
- 此处为请求参数验证 jsr303, 使用`@Valid` `@NotNull`等注解对请求参数进行验证

## 语义化
- 下面代码描述了一个请求参数转换为实体类的过程,代码很简单
```java
@PostMapping
public User addUser(UserInputDTO userInputDTO){
    User user = new User();
    BeanUtils.copyProperties(userInputDTO,user);
    return userService.addUser(user);
}
```
- 这里有人会说为什么不用`User`作为参数列表,原因:语义不同.每一个对象都有自己的有效作用域.
- 说一说上面代码的不足,两行代码很短了.还有什么不足呢,这里是一个`add`方法,有可能存在另外的方法应该再次提取方法
```java
@PostMapping
public User addUser(UserInputDTO userInputDTO){
    return userService.addUser(transform(userInputDTO));
}

private User transform(UserInputDTO userInputDT){
    User user = new User();
    BeanUtils.copyProperties(userInputDTO,user);
    return user;
}
```
### 减少二义性
- 常见例子`接口` 有人认为是 REST API 接口 有人认为是 JAVA 中的 `interface`


## 抽象接口
- 项目中肯定存在多个对象转换的方法这个时候需要抽象提取接口
```java
public interface Convert<S,T> {
    T convert(S s);
}
```
- 这样子的抽象似乎解决了问题,但是我们忽略了一个问题,我们只有进入转出口,还缺一个出口转进入的,进一步优化
```java
/**
 *
 * @param <S> source
 * @param <T> target
 */
public interface Converter<S, T> {
    T doForward(S s);

    S doBackward(T t);
}


public class User {
    private String name;
}

public class UserInputDTO extends User {
    private String mail;

    private static class UserInputDTOConverter implements Converter<User, UserInputDTO> {
        @Override
        public UserInputDTO doForward(User user) {
            UserInputDTO userInputDTO = new UserInputDTO();
            BeanUtils.copyProperties(user, userInputDTO);
            return userInputDTO;
        }

        @Override
        public User doBackward(UserInputDTO userInputDTO) {
            User user = new User();
            BeanUtils.copyProperties(userInputDTO, user);
            return user;
        }
    }

}

```

## 函数 method
1. 尽可能的减少做的事情
2. 参数列表减少,超量参数列表使用类进行封装


