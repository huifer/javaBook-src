package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.SummaryProjectPO;
import com.huifer.jenkinsspringboot.entity.result.ProInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SummaryProjectPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryProjectPO record);

    int insertSelective(SummaryProjectPO record);

    SummaryProjectPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryProjectPO record);

    int updateByPrimaryKey(SummaryProjectPO record);

    SummaryProjectPO findByProject(@Param("day") String day, @Param("name") String name);


    List<SummaryProjectPO> findByProjectAndApiKey(@Param("day") String day, @Param("name") String name, @Param("apiKey") String apiKey);

    List<SummaryProjectPO> groupByDaySumSeconds();

    List<ProInfo> sumProject();

    List<SummaryProjectPO> sumApiAndPro(@Param("apiKey") String apiKey);

    List<String> findProject();
}