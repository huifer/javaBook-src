package com.huifer.bilibili.redis;

import com.huifer.bilibili.redis.base.CacheService;
import com.huifer.bilibili.redis.entity.UserEntity;
import com.huifer.bilibili.redis.entity.bo.UserInfo;

/**
 * 用户缓存操作
 */
public interface UserCacheService extends CacheService<UserEntity> {

    /**
     * 这部分作为业务交互,或者说是服务提供方
     */
    UserInfo getUser(int i);

}
