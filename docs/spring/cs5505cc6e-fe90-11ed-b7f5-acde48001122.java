package com.huifer.mybatis.mapper;

import com.huifer.mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {
    int ins(Person person);

    List<Person> list(@Param("iid") Integer id);

}
