package org.huifer.rbac.label;

/**
 * 转换接口
 * @param <T> 目标对象
 */
public interface Convert<T> {
    /**
     * 转换
     * @return 转换对象
     */
    T convert();
}
