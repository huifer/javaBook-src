package com.github.huifer.simple.shiro.boot.service.impl;

import com.github.huifer.simple.shiro.boot.ShiroApp;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.service.UserService;
import java.util.List;
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
    userCreateParam.setUsername("huifer");
    userCreateParam.setPassword("1234567");

    boolean b = userService.userCreate(userCreateParam);
    assert b;
  }

  @Test
  void queryRolesForUsername() {
    List<String> admin = userService.queryRolesForUsername("admin");
    assert !admin.isEmpty();
  }

  @Test
  void queryPermissionsForUsername() {
    List<String> admin = userService.queryPermissionsForUsername("admin");
    assert !admin.isEmpty();
  }


}