# 自定义 Realm 
本节将介绍在Shiro中如何自定义Realm. 

## 实现自定义认证
在Shiro中关于Realm的认证和授权操作分别由下面两方法负责：
1. 认证Realm： org.apache.shiro.realm.AuthenticatingRealm.doGetAuthenticationInfo
2. 授权Realm: org.apache.shiro.realm.AuthorizingRealm.doGetAuthorizationInfo

在实际开发中如果想要自定义Realm的实现只需要继承AuthorizingRealm类即可，并不需要继承上述的两个类，原因是AuthorizingRealm类继承了AuthenticatingRealm。具体继承关系如图所示：

![AuthorizingRealm](images/AuthorizingRealm.png)

在整理清楚自定义实现方法后下面开始代码编写，创建自定义Realm类，类名为CustomerRealm，具体代码如下：

```java
public class CustomerRealm extends AuthorizingRealm {

  private static final Logger log = LoggerFactory.getLogger(CustomerRealm.class);

  /**
   * 授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
      PrincipalCollection principals) {
    return null;
  }

  /**
   * 认证
   *
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {

    return null;
  }
}
```

在这样一段代码中开发者需要在其中填写自定义的处理逻辑，下面对认证过程的自定义处理进行编写。在认证中最简单的处理方式可以是用户名密码校验，那么在doGetAuthenticationInfo方法中需要解决的问题是如何获取用户名和密码，首先进行用户名获取，在AuthenticationToken接口中提供了getPrincipal方法，该方法就是用于获取用户名的，但是返回值是Object，通常情况下用户名是String类型，因此需要做一个转换，关于用户名的验证代码如下：

```java
Object principal = token.getPrincipal();
if (String.valueOf(principal).equals("admin")) {
  return null;
}
return null;
```

在上述代码中通过硬编码的方式认为用户名必是admin才通过，在上述代码中即便用户名验证通过返回值还是null，下面要对返回值进行处理，返回对象是AuthenticationInfo接口，在Shiro中AuthenticationInfo接口有一个简单实现，这个实现类是SimpleAuthenticationInfo，在这里就直接采用该类作为返回值，下面对SimpleAuthenticationInfo的构造函数进行说明，先看构造函数的代码：

```java
public SimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
    this.principals = new SimplePrincipalCollection(principal, realmName);
    this.credentials = credentials;
}
```

在这个构造函数中需要传递三个参数三个参数的含义如下：

1. principal：该参数表示用户名。
2. credentials：该参数表示密码。
3. realmName：该参数表示Realm。

在上述三个参数中principal应该填写token中所携带的数据，credentials应该携带正确的密码，realmName填写具体的Realm，如果不确定可以使用this.getName作为默认值。明确这些内容后下面将代码补充完整，补充后代码如下：

```
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    throws AuthenticationException {
  Object principal = token.getPrincipal();
  log.info("username = [{}]", principal);
  if (String.valueOf(principal).equals("admin")) {
    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,
        "admin", getName());
    return simpleAuthenticationInfo;
  }
  return null;
}
```

完成简单的用户名验证后编写测试用例，具体测试用例方法如下：

```java
@Test
public void testCustomerRealm(){
  DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
  defaultSecurityManager.setRealm(new CustomerRealm());
  SecurityUtils.setSecurityManager(defaultSecurityManager);
  Subject subject = SecurityUtils.getSubject();
  UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
  subject.login(usernamePasswordToken);
}
```

在这个测试用例中执行结果是一个正常的处理，并不会抛出异常，下面将UsernamePasswordToken("admin", "admin")代码中的第一个参数admin修改成非admin在执行测试方法会看到如下结果：

```
org.apache.shiro.authc.UnknownAccountException: Realm [com.github.huifer.shiro.CustomerRealm@3cb5cdba] was unable to find account data for the submitted AuthenticationToken [org.apache.shiro.authc.UsernamePasswordToken - admin1, rememberMe=false].
```

此时抛出的异常是UnknownAccountException（未知的用户），表示用户名验证失败，以此可以说明在doGetAuthenticationInfo方法中对于用户名的验证是真确的。下面将UsernamePasswordToken("admin", "admin")代码中的第二个参数admin修改成非admin在执行测试方法会看到如下结果：

```
org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - admin, rememberMe=false] did not match the expected credentials.
```

此时抛出的异常是IncorrectCredentialsException，表示密码错误。注意在本例中仅作为演示用例来表示自定义认证的处理过程，具体项目开发中各位可以按照各自的需求进行更多的自定义处理，下面举一个简单例子，在前文提到的用户名验证如果在系统中用户名允许存在多个的情况下（用户名不唯一）那么原有的根据用户名进行查询这个逻辑是存在问题的，需要通过用户名和密码同时作为查询条件进行，修改后可以得到如下代码：

```java
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    throws AuthenticationException {
  // 认证方式: 从token对象中获取用户名和密码判断是否和db相同
  // 用户名
  Object principal = token.getPrincipal();
  // 密码
  Object credentials = token.getCredentials();

  User user = userMapper.findByUserNameAndPassword(principal, credentials);
  if (user != null) {
    SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,
        "admin", getName());
  }
  return  null;
}
```





## 密码处理

本节将介绍Shiro中的密码处理。在前文讲述自定义认证的过程中密码是通过明文的方式在代码中直接硬编码编写的，具体可以从下面代码中发现

```java
SimpleAuthenticationInfo(principal,"admin", getName());
```

在上述代码中明文存储了admin密码，这样的处理操作是不具备安全性的，需要对其进行修正。在Shiro中提供了处理方案，本节将采用MD5+salt的方式进行密码加密处理，从而提高安全性。首先需要确定密码处理的场景，在实际开发中关于密码的加密处理通常是在账号注册时进行的，下面将围绕账号注册进行相关实践。

第一步：创建一个用于存储用户数据的容器，具体容器如下：

```java
Map<String, String> userMap = new HashMap<>(16);
```

在这个容器中key表示用户名，value表示密码。

第二步：制作一个注册用户的方法，具体代码如下：

```java
public void register(String username, String password) {
    userMap.put(username, password);
}
```

在上述代码中直接将用户名密码保存到了容器中下面需要对其进行改进。首先进行MD5的改进，在Shiro中可以通过Md5Hash这个类进行处理，具体处理操作如下：

```java
Md5Hash md5Hash2 = new Md5Hash("password");
String hex = md5Hash2.toHex();
```

上述代码是一个基本的MD5处理，下面对salt进行实际演示，具体代码如下：

```java
Md5Hash md5Hash2 = new Md5Hash("password","salt");
String hex = md5Hash2.toHex();
```

注意：salt在本例中采用静态变量，实际开发中请采用随机值提高安全性。

在Md5Hash类中还提供了hash散列的具体操作，具体代码如下：

```java
Md5Hash md5Hash2 = new Md5Hash("password","salt",1024);
String hex = md5Hash2.toHex();
```

修改register方法，具体修改后代码如下：

```java
public void register(String username, String password) {
  Md5Hash md5Hash = new Md5Hash(password, "salt", 1024);
  userMap.put(username, md5Hash.toHex());
}
```

第三步：实现AuthorizingRealm类，并重写doGetAuthenticationInfo方法，具体实现代码如下：

```java
@Override
protected AuthenticationInfo doGetAuthenticationInfo(
    AuthenticationToken token) throws AuthenticationException {
    Object principal = token.getPrincipal();
    String password = this.userMap.get(String.valueOf(principal));
    if (password != null) {
        return new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes("salt"), getName());
    }
    return null;
}

```

在这段代码中中需要注意的是SimpleAuthenticationInfo构造方法的第三个参数，该参数代表了salt值。

第四步：测试用例编写，具体测试代码如下：

```java
@Test
public void testMD5Realm() {
  DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
  MD5Realm realm = new MD5Realm();

  HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
  credentialsMatcher.setHashIterations(1024);
  realm.setCredentialsMatcher(credentialsMatcher);


  defaultSecurityManager.setRealm(realm);
  SecurityUtils.setSecurityManager(defaultSecurityManager);
  Subject subject = SecurityUtils.getSubject();

  realm.register("admin", "admin");
  UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
  subject.login(usernamePasswordToken);
}
```

在这段代码中需要关注的代码是下面几行：

```java
HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
credentialsMatcher.setHashIterations(1024);
realm.setCredentialsMatcher(credentialsMatcher);
```

在这三行代码中主要目的是创建一个密码验证器，在这里采用的密码验证器是HashedCredentialsMatcher，具体的算法是md5，hash散列的计算次数是1024，这些内容需要和register方法中的处理相互匹配。

通过上述四个操作就完成了密码的设置密码的验证相关内容，注意在实际开发中salt的数据值应该做到一个用户一个从而提高密码破解难度和安全性。用户表可以包含下面字段：

1. username，用户名。
2. password，密码，非明文密码，通过加密工具加密后的数据。
3. salt，盐。







## 授权

本节将对Shiro中关于授权相关内容进行实际演示。在软甲开发中对于授权的定义可以简单理解为谁可以做什么。在授权场景中一般会有下面三个主要对象：

1. Subject，主体，常规情况下是用户。
2. Resource，资源，常见的资源有菜单，按钮，接口地址。
3. Permission，权限，常见的权限有可读可写，不可读不可写。

在授权方式中一般有如下两种权限管理模式：

1. 基于角色的权限控制。

   基于角色的权限控制一般情况下会和role相关联，在现实生活中比较常见的有这样一个例子：超级管理员可以进行访问。

2. 基于资源的权限控制。

   基于资源的权限控制在整体管控粒度中更加细致，一般情况下对资源而言有增删改查操作。

在Shiro中基于资源的权限控制还涉及到权限字符串的一个概念，在Shiro中权限字符串的规则是“资源标识符”+“:”+操作+“:”+“资源标识符”。在这个规则中”:”表示分隔符，资源标识符可以使用“*”进行通配符表达，表示所有。

下面将使用Shiro进行编码实战，本例的授权操作将在MD5Realm类中进行处理，主要重写doGetAuthorizationInfo方法，首先编写基于角色的权限控制代码，具体代码如下：

```java
@Override
protected AuthorizationInfo doGetAuthorizationInfo(
    PrincipalCollection principals) {

  // 主要的身份信息
  Object primaryPrincipal = principals.getPrimaryPrincipal();
  log.info("主身份是 =[{}]", primaryPrincipal);

  SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
  simpleAuthorizationInfo.addRole("admin");
  simpleAuthorizationInfo.addRole("test");
  return simpleAuthorizationInfo;
}
```

在这段代码中将通过硬编码将两个角色赋予给了SimpleAuthorizationInfo对象，具体通过addRole方法进行设置，在实际开发中需要通过主要的身份信息在数据库中进行查询对应数据在进行复制操作。上述代码对应的测试用例如下：

```java
@Test
public void withRole() {
  DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
  MD5Realm realm = new MD5Realm();
  HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
  credentialsMatcher.setHashIterations(1024);
  realm.setCredentialsMatcher(credentialsMatcher);
  defaultSecurityManager.setRealm(realm);
  SecurityUtils.setSecurityManager(defaultSecurityManager);
  Subject subject = SecurityUtils.getSubject();
  realm.register("admin", "admin");
  UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
  subject.login(usernamePasswordToken);
  if (subject.isAuthenticated()) {
    // 1. 基于角色进行授权
    boolean admin = subject.hasRole("admin");
    System.out.println(admin);
  }
}
```

下面将编写基于资源的权限控制的代码，具体代码如下：

```java
@Override
protected AuthorizationInfo doGetAuthorizationInfo(
    PrincipalCollection principals) {

  // 主要的身份信息
  Object primaryPrincipal = principals.getPrimaryPrincipal();
  log.info("主身份是 =[{}]", primaryPrincipal);

  SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
  simpleAuthorizationInfo.addStringPermission("user:*:*");
  return simpleAuthorizationInfo;
}
```

在上述代码中通过addStringPermission方法将权限字符串进行设置，这段代码对应的测试用例如下：

```java
@Test
public void withPermission(){
  DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
  MD5Realm realm = new MD5Realm();
  HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
  credentialsMatcher.setHashIterations(1024);
  realm.setCredentialsMatcher(credentialsMatcher);
  defaultSecurityManager.setRealm(realm);
  SecurityUtils.setSecurityManager(defaultSecurityManager);
  Subject subject = SecurityUtils.getSubject();
  realm.register("admin", "admin");
  UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
  subject.login(usernamePasswordToken);
  if (subject.isAuthenticated()) {
    boolean user = subject.isPermitted("user:update:01");
    System.out.println(user);
  }

}
```

通过上述两个方法的编写完成了基本的使用，在这里由于是演示用例对性能的要求不高，在实际开发过程中doGetAuthorizationInfo方法的权限查询操作会对数据库产生较大的压力，每当进行一次subject.hasRole方法或者subject.isPermitted方法都将进行一次查询操作，在doGetAuthorizationInfo方法中应该需要对数据进行缓存处理。



