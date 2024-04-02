package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroRole;
import org.apache.ibatis.annotations.Param;

/**
 * 角色表(ShiroRole)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:21
 */
public interface ShiroRoleDao extends BaseMapper<ShiroRole> {

  ShiroRole findByNameAndCode(@Param("name") String name, @Param("code") String code);
}
