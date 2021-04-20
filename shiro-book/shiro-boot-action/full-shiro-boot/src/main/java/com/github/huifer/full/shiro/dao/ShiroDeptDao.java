package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroDept;

/**
 * 部门表(ShiroDept)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:19
 */
public interface ShiroDeptDao extends BaseMapper<ShiroDept> {

  ShiroDept findByName(String name);
}
