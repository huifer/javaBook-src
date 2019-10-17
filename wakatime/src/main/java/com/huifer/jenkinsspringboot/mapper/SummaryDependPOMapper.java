package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.SummaryDependPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummaryDependPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryDependPO record);

    int insertSelective(SummaryDependPO record);

    SummaryDependPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryDependPO record);

    int updateByPrimaryKey(SummaryDependPO record);
}