package com.huifer.redis.string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class StringRedisExample {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置k , value 为 bean
     *
     * @param k    k
     * @param bean bean对象
     */
    public <T> void setStringBean(String k, T bean) {
        this.setString(k, FastJsonUtils.toJsonStr(bean));
    }

    /**
     * 获取对象
     *
     * @param k     k
     * @param clazz clazz
     */
    public <T> T getStringBean(String k, Class<T> clazz) {
        String string = this.getString(k);
        return FastJsonUtils.toBean(string, clazz);
    }

    public <T> void setList(String k, List<T> list) {
        this.setString(k, FastJsonUtils.toJsonStr(list));
    }

    public <T> List<T> getList(String k, T bean) {
        String string = this.getString(k);
        return FastJsonUtils.strToList(string, bean);
    }

    public <K, V> void setHash(String k, Map<K, V> map) {
        this.setString(k, FastJsonUtils.toBean(map));
    }

    public <K, V> Map<K, V> getHash(String k, Map<K, V> map) {
        String string = this.getString(k);
        return FastJsonUtils.strToMap(string, map);
    }


    /**
     * 设置k-v
     */
    public void setString(String k, String v) {
        stringRedisTemplate.opsForValue().set(k, v);
    }

    /**
     * 读取k下面的v
     *
     * @param k
     * @return
     */
    public String getString(String k) {
        return stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * 设置list
     */
    public void setList(String k, String v) {
        stringRedisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 删除同一个元素v
     */
    public void removeAllElement(String k, String v) {
        stringRedisTemplate.opsForList().remove(k, 0, v);
    }

    /**
     * 获取k
     */
    public List<String> getList(String k) {
        return stringRedisTemplate.opsForList().range(k, 0, -1);
    }

    /**
     * 设置hash
     * {bigK:[{k:v},{k:v}]}
     *
     * @param bigK 最外层k
     * @param k    内层k
     * @param v    内层v
     */
    public void setHash(String bigK, String k, String v) {
        stringRedisTemplate.opsForHash().put(bigK, k, v);
    }

    /**
     * 获取hash
     *
     * @param bigK
     * @return
     */
    public Map<String, String> getHash(String bigK) {
        return stringRedisTemplate.<String, String>opsForHash().entries(bigK);
    }

    /**
     * 删除内层k
     *
     * @param bigK
     * @param k
     */
    public void removeK(String bigK, String k) {
        stringRedisTemplate.opsForHash().delete(bigK, k);
    }


}
