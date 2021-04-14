package com.github.huifer.simple.shiro.boot.service.impl;

import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public boolean userCreate(UserCreateParam param) {
    return false;
  }
}
