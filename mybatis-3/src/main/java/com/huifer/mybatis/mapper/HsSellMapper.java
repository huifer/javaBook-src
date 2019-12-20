package com.huifer.mybatis.mapper;

import com.huifer.mybatis.entity.HsSell;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface HsSellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsSell record);

    int insertSelective(HsSell record);

    HsSell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsSell record);

    int updateByPrimaryKey(HsSell record);

    List<HsSell> list(@Param("ID") Integer id);

}