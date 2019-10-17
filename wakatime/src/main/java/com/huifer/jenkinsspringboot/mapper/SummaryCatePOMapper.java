package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.SummaryCatePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummaryCatePOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryCatePO record);

    int insertSelective(SummaryCatePO record);

    SummaryCatePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryCatePO record);

    int updateByPrimaryKey(SummaryCatePO record);
}