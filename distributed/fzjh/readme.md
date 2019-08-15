# 负载均衡
## 常见策略
### 轮询
>   假设当前有3台机器编号为:[机器1,机器2,机器3],此时有4个请求需要处理分发给机器,
> - 第1个请求 --> 机器1
> - 第2个请求 --> 机器2
> - 第3个请求 --> 机器3
> - 第4个请求 --> 机器1

```java
if (count < 0) {
    throw new LoadBalanceException("机器数量不能小于0");
}

int machineId = -1;
while (last < count) {
    last++;
    if (last == count) {
        last = -1;
        last++;
    }
    machineId = last;
    break;
}

ServerWeight serverWeight = serverWeights.get(machineId);
log.info("当前请求信息={},负载均衡计算后的机器ip={},端口={}", requestEntity, serverWeight.getIp(), serverWeight.getPort());
return serverWeight.getIp() + ":" + serverWeight.getPort();
```
- 随机
>   假设当前有3台机器编号为:[机器1,机器2,机器3],此时有4个请求需要处理分发给机器
> 获取一个随机值r范围(1,2,3) 随机到几就给那台机器发送
- 权重
> 假设当前有3台机器编号以及权重为:[机器1:0.2,机器2:0.4,机器3:1],此时有4个请求需要处理分发给机器,在此时设置一个随机值r
> - 第1个请求 --> r=0.5 -->  机器3
> - 第2个请求 --> r=0.1 -->  机器1
> - 第3个请求 --> r=0.9 -->  机器3
> - 第4个请求 --> r=0.3 -->  机器1
- hash %n
> 获取ip地址的hash 取模机器数量

