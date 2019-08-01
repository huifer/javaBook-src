package com.huifer.idgen.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: wang
 * @description: redis-inrc
 */
public class RedisInrcDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis();
		Long incr = jedis.incrBy("test-inrc", 1L);
		Long incr1 = jedis.incr("test-inrc");
		Long incr2 = jedis.incrBy("test-inrc", 1L);
		Long incr3 = jedis.incr("test-inrc");
		String s = jedis.get("test-inrc");
		System.out.println(s);
	}

}