package com.huifer.idgen.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author: wang
 * @description: redis-inrc
 */
@Slf4j
public class RedisInrcDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis();
		Long incr = jedis.incrBy("test-inrc", 1L);
		Long incr1 = jedis.incr("test-inrc");
		Long incr2 = jedis.incrBy("test-inrc", 1L);
		Long incr3 = jedis.incr("test-inrc");
		String s = jedis.get("test-inrc");
		log.info("{}", incr);
		log.info("{}", incr1);
		log.info("{}", incr2);
		log.info("{}", incr3);
		log.info("{}", s);

	}

}