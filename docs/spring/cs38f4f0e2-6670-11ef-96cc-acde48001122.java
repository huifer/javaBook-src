package com.huifer.githubst.mapper;


import com.huifer.githubst.entity.CommitInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommitInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommitInfo record);

    int insertSelective(CommitInfo record);

    CommitInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommitInfo record);

    int updateByPrimaryKey(CommitInfo record);
}