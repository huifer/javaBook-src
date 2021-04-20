package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.huifer.full.shiro.entity.ShiroResource;
import org.apache.ibatis.annotations.Param;

/**
 * 资源表(ShiroResource)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:20
 */
public interface ShiroResourceDao extends BaseMapper<ShiroResource> {

  ShiroResource findByNameAndAppIdAndType(@Param("name") String name, @Param("appId") Integer appId,
      @Param("type") Integer type);
}
