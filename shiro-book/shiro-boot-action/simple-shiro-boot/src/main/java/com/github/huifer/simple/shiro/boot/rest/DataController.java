package com.github.huifer.simple.shiro.boot.rest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

  @GetMapping("/authc")
  public String authc() {
    return "authc";
  }
  @GetMapping("/anon")
  public String anon() {
    return "anon";
  }

  @RequiresRoles("admin")
  @GetMapping("/data")
  public String data() {
    return "data";
  }

  @RequiresRoles("test")
  @GetMapping("/test")
  public String test() {
    return "data";
  }



  @RequiresPermissions("user:query:*")
  @GetMapping("/p1")
  public String p1() {
    return "p1";
  }

  @RequiresPermissions("user:create:*")
  @GetMapping("/p2")
  public String p2() {
    return "p2";
  }

}
