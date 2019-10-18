package com.huifer.vueadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huifer.vueadmin.entity.db.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}