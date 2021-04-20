package com.github.huifer.full.shiro.rest;

import com.github.huifer.full.shiro.model.req.company.CompanyCreateParam;
import com.github.huifer.full.shiro.model.res.ResultVO;
import com.github.huifer.full.shiro.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @PostMapping("/")
  public ResultVO create(@RequestBody CompanyCreateParam param) {
    return ResultVO.success(this.companyService.create(param));
  }

  @PutMapping("/{id}")
  public ResultVO update(@RequestBody CompanyCreateParam param, @PathVariable("id") int id) {
    return ResultVO.success(this.companyService.update(param, id));

  }

  @DeleteMapping("/")
  public ResultVO delete(@PathVariable("id") int id) {
    return ResultVO.success(this.companyService.delete(id));

  }

  @GetMapping("/{id}")
  public ResultVO byId(@PathVariable("id") int id) {
    return ResultVO.success(this.companyService.byId(id));
  }
}
