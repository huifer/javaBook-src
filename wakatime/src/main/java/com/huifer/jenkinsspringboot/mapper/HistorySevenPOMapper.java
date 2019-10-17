package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.HistorySevenPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HistorySevenPOMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(HistorySevenPO record);

    int insertSelective(HistorySevenPO record);

    HistorySevenPO selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(HistorySevenPO record);

    int updateByPrimaryKey(HistorySevenPO record);


    void deleteByApiKey(@Param("apiKey") String apiKey);

    HistorySevenPO findByApiKeyAndProName(@Param("apiKey") String apiKey, @Param("projectName") String projectName);

    int updateByApiKeyAndName(HistorySevenPO record);
}