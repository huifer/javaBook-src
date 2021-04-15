package com.github.huifer.simple.shiro.boot.service.impl;

import com.github.huifer.simple.shiro.boot.ShiroApp;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ShiroApp.class})
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  void userCreate() {
    UserCreateParam userCreateParam = new UserCreateParam();
    userCreateParam.setUsername("admin");
    userCreateParam.setPassword("admin");

    boolean b = userService.userCreate(userCreateParam);
    assert b;
  }
}