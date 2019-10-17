package com.huifer.jenkinsspringboot.mapper;

import com.huifer.jenkinsspringboot.entity.db.HeartPO;

public interface HeartPOMapper {
    int insert(HeartPO record);

    int insertSelective(HeartPO record);
}