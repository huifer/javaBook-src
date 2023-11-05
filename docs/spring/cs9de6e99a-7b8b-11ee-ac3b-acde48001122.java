package com.github.huifer.full.shiro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.huifer.full.shiro.entity.ShiroUser;
import com.github.huifer.full.shiro.model.req.user.UserCreateParam;

public interface UserService {

  boolean create(UserCreateParam param);

  boolean update(UserCreateParam param, int id);

  boolean delete(int id);

  ShiroUser byId(int id);

  Page<ShiroUser> findByUserList(String username, String loginName, Integer gender, String email,
      int start, int offset);

}
