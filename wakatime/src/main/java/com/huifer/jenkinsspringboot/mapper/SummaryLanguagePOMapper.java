package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.SummaryLanguagePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummaryLanguagePOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryLanguagePO record);

    int insertSelective(SummaryLanguagePO record);

    SummaryLanguagePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryLanguagePO record);

    int updateByPrimaryKey(SummaryLanguagePO record);
}