# zookeeper

## 演进

```sequence
用户服务--> 订单服务集群: 调用订单服务
NOTE RIGHT OF 用户服务 :维护多个wsdl
订单服务集群 --> 商品服务: 调用商品服务
NOTE RIGHT OF 商品服务 :维护多个wsdl
```

- 问题
  1. wsdl地址维护
  2. 服务集群的负载均衡
  3. 服务发现确保服务存在

```sequence
用户服务--> 中间件: 调用订单服务
note left of 中间件: 由中间件来确定具体访问哪一个订单服务
中间件 -> 订单服务1:调用订单服务
中间件 -> 订单服务2:调用订单服务
中间件 -> 订单服务3:调用订单服务
note right of 订单服务3: 订单服务内容相同

```

- 中间件数据存储

  ```mermaid
  graph TB
  
  A[/]-->B[/APP]
  B-->X[/user]
  B-->Y[/order]
  B-->Z[/commodity]
  Y-->Y-1[http://...]
  Y-->Y-2[http://...]
  Y-->Y-3[http://...]
  ```

- zookeeper集群

  ```sequence
  用户--> zookeeper_master: 用户请求
  zookeeper_master-> zookeeper_slave1: 子节点1
  zookeeper_master-> zookeeper_slave2: 子节点2
  
  
  
  ```

  负载均衡中轮询或者随机算法会让用户访问的集群不一定是同一个，从而需要满足**数据同步**.

  zookeeper：2PC数据提交



## 安装

```shell
tar -zxvf zookeeper-3.5.4-beta.tar.gz 
cd zookeeper-3.5.4-beta
cp conf/zoo_sample.cfg conf/zoo.cfg
vim conf/zoo.cfg # 按需进行配置修改
# 启动服务
sh bin/zkServer.sh start
# 启动cli
sh bin/zkCli.sh
```

## 节点

```shell
[zk: localhost:2181(CONNECTED) 11] create /oder/wsdl zhangsan
Node does not exist: /oder/wsdl

[zk: localhost:2181(CONNECTED) 12] create /order
Created /order

[zk: localhost:2181(CONNECTED) 13] create /order/wsdl zhangsan
Created /order/wsdl

[zk: localhost:2181(CONNECTED) 14] get /order/wsdl
zhangsan

[zk: localhost:2181(CONNECTED) 20] get -s /order/wsdl
zhangsan
cZxid = 0x573
ctime = Wed Jun 12 08:54:57 CST 2019
mZxid = 0x573
mtime = Wed Jun 12 08:54:57 CST 2019
pZxid = 0x573
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 8
numChildren = 0
```

### 临时节点

```
 create -e /temp temp
```

### 有序节点

```
[zk: localhost:2181(CONNECTED) 28]  create -s  /seq 1
Created /seq0000000036
[zk: localhost:2181(CONNECTED) 29]  create -s  /seq 2
Created /seq0000000037
[zk: localhost:2181(CONNECTED) 30]

```

## 集群

- 启动三台虚拟机修改zoo.cfg,每一台都需要添加如下内容

  `server.id=ip:port:port`

```
server.1=192.168.1.106:2888:3888
server.2=192.168.1.107:2888:3888
server.3=192.168.1.108:2888:3888
```

- 每一台虚拟机创建dataDir

  ![1560308603886](assets/1560308603886.png)

  **不要放在/tem**

  - `vim /tmp/zookeeper/myid`
  - 根据对应ip 写对应id

- 选择`192.168.1.106`启动zk

- 查看日志

  ```
  cat logs/zookeeper-root_h1-server-root.out 
  ```

  ![1560307105450](assets/1560307105450.png)

  ```
  2019-06-12 02:37:46,233 [myid:1] - WARN  [QuorumPeer[myid=1](plain=/0:0:0:0:0:0:0:0:2181)(secure=disabled):QuorumCnxManager@660] - Cannot open channel to 3 at election address /192.168.1.108:3888
  
  ```

  - 缺少其他服务器

- 关闭防火墙

  ```shell
  ufw disable
  ```

- 启动`192.168.1.107`     zookeeper

- 启动`192.168.1.108`     zookeeper

- 任意一台虚拟机创建一个节点

  ```
  create -s /hello0000000000
  ```

  ![1560308522848](assets/1560308522848.png)







----





