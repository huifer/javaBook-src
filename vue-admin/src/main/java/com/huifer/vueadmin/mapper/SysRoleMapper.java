package com.huifer.vueadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huifer.vueadmin.entity.db.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<String> getRoleByUserId(@Param("userId") Integer userId);
}