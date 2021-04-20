package com.github.huifer.full.shiro.model.req.dept;

import lombok.Data;

@Data
public class DeptCreateParam {

  private Integer companyId;

  private String name;

  private Integer pid;
  private Integer mainUser;


}
