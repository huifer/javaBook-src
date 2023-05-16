package com.github.huifer.full.shiro.rest;

import com.github.huifer.full.shiro.model.req.user.UserCreateParam;
import com.github.huifer.full.shiro.model.res.ResultVO;
import com.github.huifer.full.shiro.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResultVO create(@RequestBody UserCreateParam param) {
    return ResultVO.success(this.userService.create(param));
  }

  @PutMapping("/{id}")
  public ResultVO update(@RequestBody UserCreateParam param, @PathVariable("id") int id) {
    return ResultVO.success(this.userService.update(param, id));

  }

  @DeleteMapping("/")
  public ResultVO delete(@PathVariable("id") int id) {
    return ResultVO.success(this.userService.delete(id));

  }

  @GetMapping("/{id}")
  public ResultVO byId(@PathVariable("id") int id) {
    return ResultVO.success(this.userService.byId(id));
  }

  @PostMapping("/login")
  public ResultVO login(
      @RequestBody Object o
  ) {
    Map<String, Object> map = new HashMap<>();
    map.put("token", 1);
    map.put("roles", new String[]{"admin"});
    map.put("introduction", "administrator");
    map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    map.put("name", "Admin");
    return ResultVO.success(map);
  }

  @GetMapping("info")
  public ResultVO info(
      @RequestParam("token") String token
  ) {
    Map<String, Object> map = new HashMap<>();
    map.put("roles", new String[]{"admin"});
    map.put("introduction", "administrator");
    map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    map.put("name", "Admin");
    return ResultVO.success(map);
  }

  @PostMapping("logout")
  public ResultVO logout() {
    return ResultVO.success();
  }
}
