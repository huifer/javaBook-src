package com.huifer.redis.top;


import java.util.Set;

public interface TopInterface {
    /**
     * 增加指定名称的分数
     *
     * @param name  名称
     * @param score 分数
     */
    void addScore(String key, String name, int score);

    /**
     * 获取前topSize
     */
    Set<String> getTop(String key, int topSize);

    /**
     * 获取 start - limit 之间
     *
     * @param start
     * @param limit
     * @return
     */
    Set<String> getLimit(String key, int start, int limit);


}
