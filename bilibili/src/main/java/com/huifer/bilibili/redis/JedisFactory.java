package com.huifer.bilibili.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.ReentrantLock;

public class JedisFactory {
    private static ReentrantLock lock = new ReentrantLock();
    static Jedis  instance = null;

    private JedisFactory() {
    }

    public static Jedis conn() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = initRedisConn();
            }
            lock.unlock();

        }
        return instance;
    }

    private static Jedis initRedisConn() {
        instance = new Jedis("localhost", 6379);
        return instance;
    }
}
