# Java Web相关

## Jsp 内置对象

1. request
   1. 客户端请求 ， 包含 请求参数
2. response
   1. 服务端响应
3. pageContext
   1. 页面对象
4. session
   1. 会话信息
5. application
   1. 服务器运行环境对象
6. out
   1. 输出服务器响应输出流的对象
7. page
   1. JSP页面自己
8. exception
   1. 页面异常对象
9. config
   1. web应用配置对象





## JSP 作用域

1. page
2. request
3. session
4. application



## Session 和Cookie 区别

### 存储位置

- Session 服务端
- Cookie 浏览器

### 安全性

- cookie 可以伪造

### 数量

- cookie 有容量限制

## 存储

- session 存储 redis 数据库 应用程序
- cookie 存储 浏览器中



## cookie 禁用 如何实现session

- 将`session_id` 作为一个请求参数进行传输从数据库中获取对应的session信息



## 避免sql注入

1. 使用PreparedStatement 
2. 正则过滤特殊字符



## 301 302 区别

- 301
  - 永久重定向
- 302
  - 暂时重定向





## forward 和 redirect 区别

forward ：转发

redirect：重定向

|          | forward         | redirect   |
| -------- | --------------- | ---------- |
| url      | url不改变       | url改变    |
| 数据信息 | 共享request数据 | 不共享数据 |





## post 和get 的区别

|            | get              | post              |
| ---------- | ---------------- | ----------------- |
| 浏览器缓存 | 浏览器会主动缓存 | 不会缓存          |
| 参数       | 有参数大小的限制 | 没有参数大小限制  |
| 安全性     | 明文显示在url中  | post不再url中显示 |



## 跨域如何实现

- 服务端跨域 cors 等于 *
- 接口注解 `@CrossOrigin`
- `jsonp`跨域

### jsonp 原理

1. 首先是利用script标签的src属性来实现跨域。
2. 前端方法作为参数传给服务端，服务端将数据制作完成返回



