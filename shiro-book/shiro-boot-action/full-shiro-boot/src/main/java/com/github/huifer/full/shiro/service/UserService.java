package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroUserEntity;
import com.github.huifer.full.shiro.model.req.user.UserCreateParam;

public interface UserService {

  ShiroUserEntity create(UserCreateParam param);

  ShiroUserEntity update(UserCreateParam param, int id);

  boolean delete(int id);


}
