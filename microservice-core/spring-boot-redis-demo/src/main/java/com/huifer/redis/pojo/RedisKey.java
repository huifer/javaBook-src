package com.huifer.redis.pojo;

import org.springframework.data.redis.connection.DataType;

import java.util.Objects;


public class RedisKey implements Comparable {

    private Object key;
    private DataType dataType;
    private Object value;

    @Override
    public int compareTo(Object o) {
        if (o == null) return 1;

        if (o instanceof RedisKey) {
            RedisKey rko = (RedisKey) o;
            return this.getDataType().compareTo(rko.getDataType());
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisKey redisKey = (RedisKey) o;
        return key.equals(redisKey.key) &&
                dataType == redisKey.dataType &&
                value.equals(redisKey.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, dataType, value);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "RedisKey{" +
                "key=" + key +
                ", dataType=" + dataType +
                ", value=" + value +
                '}';
    }
}
