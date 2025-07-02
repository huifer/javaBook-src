package com.github.huifer.full.shiro.rest;

import com.github.huifer.full.shiro.model.req.resourece.ResourceCreateParam;
import com.github.huifer.full.shiro.model.res.ResultVO;
import com.github.huifer.full.shiro.service.ResourceService;
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
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private ResourceService resourceService;

  @PostMapping("/")
  public ResultVO create(@RequestBody ResourceCreateParam param) {
    return ResultVO.success(this.resourceService.create(param));
  }

  @PutMapping("/{id}")
  public ResultVO update(@RequestBody ResourceCreateParam param, @PathVariable("id") int id) {
    return ResultVO.success(this.resourceService.update(param, id));

  }

  @DeleteMapping("/")
  public ResultVO delete(@PathVariable("id") int id) {
    return ResultVO.success(this.resourceService.delete(id));

  }

  @GetMapping("/{id}")
  public ResultVO byId(@PathVariable("id") int id) {
    return ResultVO.success(this.resourceService.byId(id));
  }
}
