package com.github.huifer.full.shiro.model.res.user;

import java.util.List;
import lombok.Data;

@Data
public class UserInfo {

  private String loginName;

  private String username;

  private String email;

  private Integer gender;

  List<UserCompany> companyList;




}
