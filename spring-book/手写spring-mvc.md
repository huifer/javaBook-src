# 手写 spring mvc 
## 设计思想
### 配置
- 外部配置文件
- 类似spring注解编写
### 初始化
- 读取配置
- 包扫描
- IOC 容器初始化并放入具体的数据
- 简单的 handlerMapping 初始化
    - key: url ,value: method

### 运行
- httpServlet 接口调用 `doPost`&`doGet`
- 从 handlerMapping 根据 url 获取 method
- 得到 method 通过反射调用
- 写出结果