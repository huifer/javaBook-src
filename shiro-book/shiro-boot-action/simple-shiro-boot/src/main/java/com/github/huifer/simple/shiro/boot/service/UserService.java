package com.github.huifer.simple.shiro.boot.service;

import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;

public interface UserService {
  boolean userCreate(UserCreateParam param);
}
