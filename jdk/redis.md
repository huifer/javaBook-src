# redis 问题
## Redis 与 memcached 相比有哪些优势？
> memcached 只能存储字符串, Redis 存储类型多样
> Redis 速度快
> Redis 可以数据持久化


## Redis 支持哪几种数据类型？
> - string
> - list
> - hash
> - zset
> - set

## Redis 有哪几种数据淘汰策略？
> 1. 内存到达限制,谁也不删除,返回错误
> 1. 回收最少使用的key
> 1. 回收最少使用的key,且key必须在过期集合中
> 1. 随机回收
> 1. 随即回收,且在过期集合中
> 1. 回收过期集合中存活时间短的


## 为什么 Redis 需要把所有数据放到内存中?
> 读写速度考虑.

## Redis 一个字符串类型的值能存储最大容量是多少？
> - 512 mb

## Redis 主要消耗什么物理资源？
> 内存资源

## Redis 集群方案应该怎么做？都有哪些方案？
> 1. 主从复制
> 1. 哨兵模式
> 1. Redis-Cluster

## 说说 Redis 哈希槽的概念？
> 存在16384 个哈希槽,每一个key经过CRC15校验对13684取模,判断落入哪一个槽内

## Redis 有哪些适合的场景？
> - session 
> - 队列
> - 排行榜
> - 发布者/订阅者模式
## Redis 支持的 Java 客户端都有哪些？官方推荐用哪个？
> - Jedis
> - Redisson
> 官方推荐Redisson