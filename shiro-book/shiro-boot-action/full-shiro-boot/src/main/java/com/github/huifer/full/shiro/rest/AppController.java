package com.github.huifer.full.shiro.rest;

import com.github.huifer.full.shiro.model.req.app.AppCreateParam;
import com.github.huifer.full.shiro.model.res.ResultVO;
import com.github.huifer.full.shiro.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private AppService appService;

  @PostMapping("/")
  public ResultVO create(@RequestBody AppCreateParam param) {
    boolean b = this.appService.create(param);
    return ResultVO.success(b);
  }

  @PutMapping("/update/{id}")
  public ResultVO update(
      @RequestBody AppCreateParam param,
      @PathVariable("id") int id) {
    boolean update = appService.update(param, id);
    return ResultVO.success(update);

  }

  @DeleteMapping("/{id}")
  public ResultVO delete(@PathVariable("id") int id) {
    return ResultVO.success(this.appService.delete(id));
  }

  @GetMapping("/{id}")
  public ResultVO byId(@PathVariable("id") int id) {
    return ResultVO.success(this.appService.byId(id));

  }
}
