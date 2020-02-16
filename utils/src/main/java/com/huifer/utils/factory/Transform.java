package com.huifer.utils.factory;

/**
 * 转换接口
 * @param <T> 目标类型
 */
public interface Transform< T> {
    T transform(String s);
}
