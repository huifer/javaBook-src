package com.huifer.githubst.mapper;

import com.huifer.githubst.entity.RepoInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RepoInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepoInfo record);

    int insertSelective(RepoInfo record);

    RepoInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RepoInfo record);

    int updateByPrimaryKey(RepoInfo record);
}