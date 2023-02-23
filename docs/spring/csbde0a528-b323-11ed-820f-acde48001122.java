package com.huifer.bilibili.redis.base;

/**
 * 提供独立CRUD
 * 共享操作,建议是一个key一个操作对象
 *
 * @param <T>
 */
public interface CacheService<T> {
    void add(T t);

    T delete(T t);

    T update(T t);

    Object getAll();
}
