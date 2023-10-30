package com.huifer.redis.pojo;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;

public class RedisValue {
    /**
     * 值
     */
    private Object value;
    /**
     * 分数zSet
     */
    private Double score;
    /**
     * hash 的字段
     */
    private Object field;

    /*
     * 用于字符串存放结果对象
     * @param value
     */
    public RedisValue(Object value) {
        this.value = value;
    }

    /**
     * zSet 使用
     *
     * @param value
     * @param score
     */
    public RedisValue(Object value, Double score) {
        this.value = value;
        this.score = score;
    }


    /**
     * hash使用
     *
     * @param value
     * @param field
     */
    public RedisValue(Object value, Object field) {
        this.value = value;
        this.field = field;
    }

    /**
     * 用于hash存储结果对象
     *
     * @param map redis存储Map对象
     * @return Object
     */
    public static Object createHashValue(Map<Object, Object> map) {
        List<RedisValue> rs = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object k = entry.getKey();
            Object v = entry.getValue();
            rs.add(new RedisValue(v, k));
        }
        return rs;
    }

    /**
     * 用于set存放结果对象
     *
     * @param set redis存储set对象
     * @return Object
     */
    public static Object createSetValue(Set<Object> set) {
        Set<RedisValue> rs = new HashSet<>();
        for (Object o : set) {
            rs.add(new RedisValue(o));
        }
        return rs;
    }

    /**
     * 用于zSet存放结果对象
     *
     * @param zSet redis存储zSet对象
     * @return Object
     */
    public static Object createZSetValue(Set<ZSetOperations.TypedTuple<Object>> zSet) {
        List<RedisValue> rs = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> o : zSet) {
            rs.add(new RedisValue(o.getValue(), o.getScore()));
        }
        return rs;
    }

    /**
     * 用于list存放结果对象
     *
     * @param value redis存储list对象
     * @return Object
     */
    public static Object createListRedisValue(List<Object> value) {
        List<RedisValue> rs = new ArrayList<>();
        for (Object o : value) {
            rs.add(new RedisValue(o));
        }
        return rs;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "RedisValue{" +
                "value=" + value +
                ", score=" + score +
                ", field=" + field +
                '}';
    }
}
