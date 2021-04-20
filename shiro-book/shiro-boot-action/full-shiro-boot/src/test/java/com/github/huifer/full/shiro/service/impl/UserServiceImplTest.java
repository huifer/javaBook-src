package com.github.huifer.full.shiro.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.huifer.full.shiro.FullShiroApp;
import com.github.huifer.full.shiro.entity.ShiroUser;
import com.github.huifer.full.shiro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author huifer
 */
@SpringBootTest(classes = FullShiroApp.class)
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  void findByUserList() {
    Page<ShiroUser> byUserList = userService.findByUserList("", "", null, null, 1, 1);
    System.out.println();
  }
}