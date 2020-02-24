package com.huifer.bilibili.redis.impl;

import com.alibaba.fastjson.JSON;
import com.huifer.bilibili.redis.JedisFactory;
import com.huifer.bilibili.redis.UserCacheService;
import com.huifer.bilibili.redis.entity.BookEntity;
import com.huifer.bilibili.redis.entity.UserEntity;
import com.huifer.bilibili.redis.entity.bo.UserInfo;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

public class UserCacheServiceImpl implements UserCacheService {
    public static final String USER_CACHE_KEY = "USER_CACHE_KEY";

    Jedis conn = JedisFactory.conn();

    public static void main(String[] args) {
        UserCacheServiceImpl userCacheService = new UserCacheServiceImpl();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("12");

        userCacheService.add(userEntity);

        Map<String, String> all = userCacheService.getAll();
        UserInfo user = userCacheService.getUser(1);
        System.out.println();
    }


    @Override
    public UserInfo getUser(int i) {
        UserEntity userEntity = JSON.parseObject(this.getAll().get(String.valueOf(i)), UserEntity.class);
        BookCacheServiceImpl bookCacheService = new BookCacheServiceImpl();
        List<BookEntity> all = bookCacheService.getAll();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserEntity(userEntity);
        userInfo.setBookEntity(all);

        return userInfo;
    }

    @Override
    public void add(UserEntity userEntity) {
        conn.hset(USER_CACHE_KEY, userEntity.getId().toString(), userEntity.toJson());
    }

    @Override
    public UserEntity delete(UserEntity userEntity) {
        // todo
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        // todo
        return null;
    }

    @Override
    public Map<String, String> getAll() {
        // todo
        Map<String, String> stringStringMap = conn.hgetAll(USER_CACHE_KEY);
        return stringStringMap;
    }

}
