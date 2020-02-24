package com.huifer.bilibili.redis.base;

public interface StringToEntity<T> {
    T convert(String s);

    String toJson();
}
