package com.huifer.redis.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FastJsonUtils {

    private FastJsonUtils() {
        throw new RuntimeException("this is a utils");
    }

    /**
     * bean to jsonStr
     *
     * @param bean bean
     */
    public static <T> String toJsonStr(T bean) {
        return JSON.toJSONString(bean);
    }

    /**
     * jsonStr to bean
     *
     * @param jsonStr jsonStr
     * @param clazz   clazz
     * @return bean
     */
    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * map to jsonStr
     *
     * @param map map
     * @return jsonStr
     */
    public static <K, V> String toBean(Map<K, V> map) {
        return JSON.toJSONString(map);
    }

    /**
     * list to jsonStr
     *
     * @param list list
     * @return jsonStr
     */
    public static <T> String toJsonStr(List<T> list) {
        return JSON.toJSONString(list);
    }

    /**
     * jsonStr to map
     *
     * @param jsonStr jsonStr
     * @param map     map
     * @return {@link Map}
     */
    public static <K, V> Map<K, V> strToMap(String jsonStr, Map<K, V> map) {
        Map<K, V> kvMap = JSON.parseObject(jsonStr, new TypeReference<Map<K, V>>() {
        });
        Map<K, V> result = new HashMap<>();
        Class<?> aClass = map.values().stream().collect(Collectors.toList()).get(0).getClass();
        Map<K, V> m = new HashMap<>();
        kvMap.forEach(
                (k, v) -> {
                    JSONObject jsonObject = (JSONObject) v;
                    V o = (V) jsonObject.toJavaObject(aClass);

                    m.put(k, o);
                }
        );

        return m;
    }

    /**
     * jsonStr to list
     *
     * @param jsonStr jsonStr
     * @param bean    bean
     * @return {@link List}
     */
    public static <T> List<T> strToList(String jsonStr, T bean) {
        List<JSONObject> list = JSON.parseObject(jsonStr, new TypeReference<List<JSONObject>>() {

        });
        List<T> res = new ArrayList<>();
        for (JSONObject jsonObject : list) {
            T o = (T) jsonObject.toJavaObject(bean.getClass());
            res.add(o);
        }
        return res;
    }


}
