package com.github.huifer.simple.shiro.boot.rest;

import com.github.huifer.simple.shiro.boot.model.Result;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @PostMapping("/")
  public Result userCreate(
      @RequestBody UserCreateParam param
  ) {
    return null;
  }
}
