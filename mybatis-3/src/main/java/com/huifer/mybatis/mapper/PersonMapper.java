package com.huifer.mybatis.mapper;

import com.huifer.mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    int insert(Person person);
}
