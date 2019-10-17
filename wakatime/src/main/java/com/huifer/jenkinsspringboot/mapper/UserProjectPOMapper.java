package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.UserProjectPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProjectPOMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserProjectPO record);

    int insertSelective(UserProjectPO record);

    UserProjectPO selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserProjectPO record);

    int updateByPrimaryKey(UserProjectPO record);


    UserProjectPO findAllConditions(@Param("userProjectPO") UserProjectPO userProjectPO);
}