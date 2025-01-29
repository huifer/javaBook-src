package com.huifer.utils.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: wang
 * @description: 表机构查询语句
 */
@Mapper
public interface GeneratorMapper {

    /**
     * 查询表结构
     *
     * @param tableName 表名,输入""查询所有
     * @return
     */
    List<Map<String, Object>> findTableDesc(
            @Param("tableName") String tableName,
            @Param("offset") int offset,
            @Param("limit") int limit

    );

    /**
     * 统计表数量
     *
     * @param tableName
     * @return
     */
    int findTableDescCount(
            @Param("tableName") String tableName
    );


    Map<String, String> queryTable(@Param("tableName") String tableName);


    List<Map<String, String>> queryColumns(@Param("tableName") String tableName);


}
