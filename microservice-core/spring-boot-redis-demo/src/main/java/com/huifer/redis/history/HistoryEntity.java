package com.huifer.redis.history;

import lombok.Data;

import java.util.List;

/**
 * 存储历史查询记录的实体类
 *
 * @param <T> 查询结果QueryResult
 */
@Data
public class HistoryEntity {
    /**
     * 查询名称
     */
    private String name;
    /**
     * 查询语句
     */
    private String sql;
    /**
     * 最后一次操作时间
     */
    private Long lastTime;
    /**
     * 最大自增长id
     */
    private Integer bigSelfPro;
    /**
     * 查询结果
     */
    private List<QueryResult> queryResult;
}
