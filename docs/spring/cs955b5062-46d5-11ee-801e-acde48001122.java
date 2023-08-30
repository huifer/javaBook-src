package com.github.huifer.full.shiro.model.req.user;

import lombok.Data;

@Data
public class UserCreateParam {
  private String loginName;

  private String username;

  private String password;

  private String email;

  private Integer gender;

}
