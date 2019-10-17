package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.UserDurationsPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDurationsPOMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserDurationsPO record);

    int insertSelective(UserDurationsPO record);

    UserDurationsPO selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserDurationsPO record);

    int updateByPrimaryKey(UserDurationsPO record);
}