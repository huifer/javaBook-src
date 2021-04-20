package com.github.huifer.full.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.huifer.full.shiro.entity.ShiroUser;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

/**
 * 员工表(ShiroUser)表数据库访问层
 *
 * @author huifer
 * @since 2021-04-18 10:08:21
 */
public interface ShiroUserDao extends BaseMapper<ShiroUser> {

  ShiroUser findShiroUserEntityByLoginName(String loginName);

  Page<ShiroUser> findByUserList(@Param("username") String username,
      @Param("loginName") String loginName, @Param("gender") Integer gender,
      @Param("email") String email, @Param("userPage") IPage<ShiroUser> userPage);
}
