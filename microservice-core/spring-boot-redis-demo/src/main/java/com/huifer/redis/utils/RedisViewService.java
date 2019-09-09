package com.huifer.redis.utils;

import com.huifer.redis.pojo.RedisKey;
import com.huifer.redis.pojo.RedisValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 描述:
 * redis可视化
 *
 * @author huifer
 * @date 2019-09-03
 */
@Service
public class RedisViewService {
    protected static final Logger log = LoggerFactory.getLogger(RedisViewService.class);
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;


    /**
     * 返回所有类
     *
     * @return {@link RedisKey} set集合
     */
    public Set<RedisKey> keys() {
        log.trace("开始获取所有redis-key");
        Set<String> keys = redisTemplate.keys("*");
        Set<RedisKey> redisKeys = new TreeSet<>();
        keys.forEach(k -> {
            RedisKey redisKey = new RedisKey();
            redisKey.setKey(k);
            redisKey.setDataType(redisTemplate.type(k));
            redisKey.setValue(getValue(k, redisTemplate.type(k)));
            redisKeys.add(redisKey);
        });
        log.info("获取到的redis-key={}", redisKeys);
        return redisKeys;
    }

    /**
     * 设置value
     *
     * @param key    key
     * @param values 值数组
     * @param scores 分数数组
     * @param fields 字段数组
     */
    public void setValue(String key, Object[] values, double[] scores, Object[] fields) {
        DataType type = redisTemplate.type(key);
        switch (type) {
            case SET:
                setSetValue(key, values);
                break;
            case HASH:
                setHashValue(key, values, fields);
                break;
            case LIST:
                setListValue(key, values);
                break;
            case ZSET:
                setZSetValue(key, values, scores);
                break;
            case STRING:
                setStringValue(key, values[values.length - 1]);
                break;
            default:
                break;

        }
    }

    /**
     * 设置string类型值
     *
     * @param key
     * @param value
     */
    public void setStringValue(String key, Object value) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.STRING)) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            // 设置value数组的最后一个
            valueOperations.set(key, value);
        }
    }

    /**
     * 设置ZSet类型值
     *
     * @param key
     * @param values
     * @param scores
     */
    public void setZSetValue(String key, Object[] values, double[] scores) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.ZSET)) {

            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            if (values.length == scores.length) {
                Set<ZSetOperations.TypedTuple<Object>> zSet = new HashSet<>();
                for (int i = 0; i < values.length; i++) {
                    Object o = values[i];
                    double sc = scores[i];
                    zSet.add(new ZSetOperations.TypedTuple<Object>() {
                        public Object object;
                        public double score;

                        {
                            object = o;
                            score = sc;
                        }

                        @Override
                        public Object getValue() {
                            return object;
                        }

                        @Override
                        public Double getScore() {
                            return score;
                        }

                        @Override
                        public int compareTo(ZSetOperations.TypedTuple<Object> o) {
                            if (object == null) {
                                return 1;
                            }
                            if (o instanceof ZSetOperations.TypedTuple) {
                                return this.getScore() - o.getScore() >= 0 ? 1 : -1;
                            }
                            return 1;
                        }
                    });
                }
                zSetOperations.add(key, zSet);
            }
        }
    }

    /**
     * 设置List类型值
     *
     * @param key
     * @param values
     */
    public void setListValue(String key, Object[] values) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.LIST)) {

            ListOperations listOperations = redisTemplate.opsForList();
            // 整个value都rightPush
            listOperations.rightPushAll(key, values);
        }

    }

    /**
     * 设置hash类型值
     *
     * @param key
     * @param values
     * @param fields
     */
    public void setHashValue(String key, Object[] values, Object[] fields) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.HASH)) {
            HashOperations hashOperations = redisTemplate.opsForHash();
            if (values.length == fields.length) {
                Map map = new HashMap();
                for (int i = 0; i < values.length; i++) {
                    Object k = fields[i];
                    Object v = values[i];
                    map.put(k, v);
                }
                hashOperations.putAll(key, map);
            }
        }
    }

    /**
     * 设置set类型值
     *
     * @param key
     * @param values
     */
    public void setSetValue(String key, Object[] values) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.SET)) {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOperations.add(key, values);
        }
    }


    /**
     * 获取redis中k-v
     *
     * @param key      需要搜索的key
     * @param dataType 当前key的类型{@link DataType}
     * @return Object value
     */
    public Object getValue(String key, DataType dataType) {
        Object value = null;
        switch (dataType) {
            case SET:
                value = getSetValue(key);
                break;
            case HASH:
                value = getHashValue(key);
                break;
            case LIST:
                value = getListValue(key);
                break;
            case ZSET:
                value = getZSetValue(key);
                break;
            case STRING:
                value = getStringValue(key);
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 获取string类型值
     *
     * @param key
     * @return
     */
    public Object getStringValue(String key) {
        Object value = null;
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.STRING)) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Object value1 = valueOperations.get(key);
            value = new RedisValue(value1);
        }
        return value;
    }

    /**
     * 获取ZSet类型值
     *
     * @param key
     * @return
     */
    public Object getZSetValue(String key) {
        Object value = null;
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.ZSET)) {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            Set set = zSetOperations.rangeWithScores(key, 0, -1);
            value = RedisValue.createZSetValue(set);
        }
        return value;
    }

    /**
     * 获取list类型值
     *
     * @param key
     * @return
     */
    public Object getListValue(String key) {
        Object value = null;
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.LIST)) {
            ListOperations listOperations = redisTemplate.opsForList();
            List range = listOperations.range(key, 0, -1);
            value = RedisValue.createListRedisValue(range);
        }
        return value;
    }

    /**
     * 获取hash类型值
     *
     * @param key
     * @return
     */
    public Object getHashValue(String key) {
        Object value = null;
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.HASH)) {
            HashOperations hashOperations = redisTemplate.opsForHash();
            Map entries = hashOperations.entries(key);
            value = RedisValue.createHashValue(entries);
        }
        return value;
    }

    /**
     * 获取set类型值
     *
     * @param key
     * @return
     */
    public Object getSetValue(String key) {
        Object value = null;
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.SET)) {
            SetOperations setOperations = redisTemplate.opsForSet();
            Set members = setOperations.members(key);
            value = RedisValue.createSetValue(members);
        }
        return value;
    }


    /**
     * 删除string的值
     *
     * @param key
     */
    public void deleteString(String key) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.STRING)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除set的值
     *
     * @param key
     * @param values
     */
    public void deleteSetValue(String key, Object[] values) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.SET)) {
            redisTemplate.opsForSet().remove(key, values);
        }
    }

    /**
     * 删除list的值1个
     *
     * @param key
     */
    public void deleteListValue(String key) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.LIST)) {
            redisTemplate.opsForList().leftPop(key);
        }
    }


    /**
     * 删除ZSet的值
     *
     * @param key
     * @param values
     */
    public void deleteZSetValue(String key, Object[] values) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.ZSET)) {
            redisTemplate.opsForZSet().remove(key, values);
        }
    }

    /**
     * 删除hash的field
     *
     * @param key
     * @param fields
     */
    public void deleteHashField(String key, Object[] fields) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.HASH)) {
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.delete(key, fields);
        }
    }

    /**
     * 删除key
     *
     * @param key
     * @param values
     * @param fields
     */
    public void delete(String key, Object[] values, Object[] fields) {
        DataType type = redisTemplate.type(key);
        switch (type) {
            case SET:
                this.deleteSetValue(key, values);
                break;
            case HASH:
                this.deleteHashField(key, fields);
                break;
            case LIST:
                this.deleteListValue(key);
                break;
            case ZSET:
                this.deleteZSetValue(key, values);
                break;
            case STRING:
                this.deleteString(key);
                break;
            default:
                break;
        }
    }

    /**
     * 修改hash
     *
     * @param key
     * @param field
     * @param value
     */
    public void updateHashFiled(String key, Object field, Object value) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.HASH)) {
            redisTemplate.opsForHash().put(key, field, value);
        }
    }

    /**
     * 修改list
     *
     * @param key
     * @param value
     */
    public void updateListValue(String key, Object value) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.LIST)) {
            redisTemplate.opsForList().rightPush(key, value);
        }
    }

    /**
     * 修改string
     *
     * @param key
     * @param value
     */
    public void updateStringValue(String key, Object value) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.STRING)) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 修改set
     *
     * @param key
     * @param value
     */
    public void updateSetValue(String key, Object value) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.SET)) {
            redisTemplate.opsForSet().add(key, value);
        }
    }

    /**
     * 修改ZSet
     *
     * @param key
     * @param value
     * @param score
     */
    public void updateZSetValue(String key, Object value, double score) {
        DataType type = redisTemplate.type(key);
        if (type.equals(DataType.ZSET)) {
            redisTemplate.opsForZSet().add(key, value, score);
        }
    }

    /**
     * 修改
     *
     * @param key
     * @param filed
     * @param value
     * @param score
     */
    public void update(String key, Object filed, Object value, double score) {
        DataType type = redisTemplate.type(key);
        switch (type) {
            case SET:
                this.updateSetValue(key, value);
                break;
            case HASH:
                this.updateHashFiled(key, filed, value);
                break;
            case LIST:
                this.updateListValue(key, value);
                break;
            case ZSET:
                this.updateZSetValue(key, value, score);
                break;
            case STRING:
                this.updateStringValue(key, value);
                break;
            default:
                break;
        }
    }


}
