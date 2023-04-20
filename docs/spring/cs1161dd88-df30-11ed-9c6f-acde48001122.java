package com.huifer.bilibili.redis;

import com.huifer.bilibili.redis.base.CacheService;
import com.huifer.bilibili.redis.entity.BookEntity;

/**
 * 书籍缓存操作,
 * 1. 第一部分为基础的对于redis-key 操作
 * 2. 独立提供操作,对其他需求方使用
 *
 */
public interface BookCacheService extends CacheService<BookEntity> {


}
