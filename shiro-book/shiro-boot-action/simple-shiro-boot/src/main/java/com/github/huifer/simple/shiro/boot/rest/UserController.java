package com.github.huifer.simple.shiro.boot.rest;

import com.github.huifer.simple.shiro.boot.model.Result;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/")
  public Result userCreate(
      @RequestBody UserCreateParam param
  ) {
    boolean b = userService.userCreate(param);
    return Result.ok("用户创建", b);
  }

  @PostMapping("/login")
  public Result login(
      @RequestBody UserCreateParam param
  ) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
        param.getUsername(),
        param.getPassword()
    );
    subject.login(usernamePasswordToken);
    return Result.ok("用户登录", true);
  }



}
