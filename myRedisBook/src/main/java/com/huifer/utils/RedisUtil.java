package com.huifer.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 描述:
 * redis util
 *
 * @author huifer
 * @date 2019-03-18
 */
public class RedisUtil {

    public static void main(String[] args) {
        poolConnect();

    }

    /**
     * redis 单例连接
     */
    public static void singleConnect() {
        Jedis jedis1 = new Jedis("localhost", 6379);
        String set = jedis1.set("testkey", "testvalue");
        System.out.println(set);
        String s1 = jedis1.get("testkey");
        System.out.println(s1);
        jedis1.close();

    }

    /**
     * jedis 连接池
     */
    public static void poolConnect() {
        JedisPool jedisPool = new JedisPool();
        Jedis resource = jedisPool.getResource();

        String s1 = resource.get("testkey");
        System.out.println(s1);
        resource.close();
        jedisPool.close();

    }

    private static JedisPool pool;


    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(10);

        pool = new JedisPool(config, "localhost", 6379);


    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * 获取锁 (set)
     *
     * @param lockKey   锁key
     * @param requestId requestid
     * @param timeout   超时
     * @return
     */
    public static synchronized boolean getLockSet(String lockKey, String requestId, Integer timeout) {

        Jedis jedis = getJedis();

        String result = jedis.set(lockKey, requestId, "NX", "EX", timeout);
        if (result.equals("OK")) {
            return true;
        }
        return false;
    }

    /**
     * getLockSetNx
     *
     * @param lockKey
     * @param requestId
     * @param timeout
     * @return
     */
    public static synchronized boolean getLockSetNx(String lockKey, String requestId, Integer timeout) {

        Jedis jedis = getJedis();
        Long setnxL = jedis.setnx(lockKey, requestId);
        if (setnxL == 1L) {
            // 设置有效期
            jedis.expire(lockKey, timeout);
            return true;
        }
        return false;
    }


    /**
     * 释放锁
     * @param lockKey
     * @param requestId
     * @param timeout
     */
    public static void releaseLock(String lockKey, String requestId, Integer timeout) {
        Jedis jedis = getJedis();
        if (requestId.equals(jedis.get(lockKey))) {
            jedis.del(lockKey);
        }

    }

}
