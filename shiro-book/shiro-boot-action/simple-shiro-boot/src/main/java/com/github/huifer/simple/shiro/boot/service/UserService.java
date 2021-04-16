package com.github.huifer.simple.shiro.boot.service;

import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import java.util.List;

public interface UserService {

  ShiroUserEntity findByUsername(String username);
  boolean userCreate(UserCreateParam param);

  List<String> queryRolesForUsername(String username);

  List<String> queryPermissionsForUsername(String username);
}
