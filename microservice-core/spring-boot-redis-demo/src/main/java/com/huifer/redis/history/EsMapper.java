package com.huifer.redis.history;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EsMapper {
    @Insert("insert into t_es (id, `name`) value (#{es.id} , #{es.name})")
    int insert(@Param("es") Es record);

    @Select("select `name` , count(`name`) as ns from t_es where self_pro > #{start} and self_pro<#{end }  group by `name`")
    @Results({
        @Result(column = "name", property = "name"),
        @Result(column = "ns", property = "ns")
    })

    List<QueryResult> query(@Param("start") int start, @Param("end") int end);


    @Select(" select table_name, AUTO_INCREMENT from information_schema.tables where table_name=#{table}  ")
    @Results({
        @Result(column = "table_name", property = "tableName"),
        @Result(column = "AUTO_INCREMENT", property = "autoIncrement")
    })
    TableStatus qTable(@Param("table") String tb);
}