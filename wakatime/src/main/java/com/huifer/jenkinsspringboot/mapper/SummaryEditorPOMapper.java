package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.SummaryEditorPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SummaryEditorPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryEditorPO record);

    int insertSelective(SummaryEditorPO record);

    SummaryEditorPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryEditorPO record);

    int updateByPrimaryKey(SummaryEditorPO record);
}