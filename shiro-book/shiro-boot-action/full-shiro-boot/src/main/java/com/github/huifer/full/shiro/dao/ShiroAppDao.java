package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroApp;

/**
 * (ShiroApp)表数据库访问层
 *
 * @author HuiFer
 * @since 2021-04-20 09:02:03
 */
public interface ShiroAppDao extends BaseMapper<ShiroApp> {

  ShiroApp findByName(String name);
}
