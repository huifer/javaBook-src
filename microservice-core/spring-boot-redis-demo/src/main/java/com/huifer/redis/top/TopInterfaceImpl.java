package com.huifer.redis.top;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TopInterfaceImpl implements TopInterface {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addScore(String key, String name, int score) {
        Double aDouble = stringRedisTemplate.opsForZSet().incrementScore(key, name, score);
    }

    @Override
    public Set<String> getTop(String key, int topSize) {
        Set<String> range = stringRedisTemplate.opsForZSet().reverseRange(key, 0, topSize - 1);
        return range;
    }

    @Override
    public Set<String> getLimit(String key, int start, int limit) {
        Set<String> range = stringRedisTemplate.opsForZSet().reverseRange(key, start - 1, limit - 1);
        return range;
    }
}
