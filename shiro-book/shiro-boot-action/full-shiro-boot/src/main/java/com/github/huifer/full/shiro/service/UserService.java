package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroUser;
import com.github.huifer.full.shiro.model.req.user.UserCreateParam;

public interface UserService {

  boolean create(UserCreateParam param);

  boolean update(UserCreateParam param, int id);

  boolean delete(int id);



}
