package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.WakaUserinfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WakaUserinfoMapper {
    int deleteByPrimaryKey(String apiKey);

    int insert(WakaUserinfoPO record);

    int insertSelective(WakaUserinfoPO record);

    WakaUserinfoPO selectByPrimaryKey(String apiKey);

    int updateByPrimaryKeySelective(WakaUserinfoPO record);

    int updateByPrimaryKey(WakaUserinfoPO record);


}