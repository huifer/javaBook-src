package com.github.huifer.full.shiro.rest;

import com.github.huifer.full.shiro.model.req.post.PostCreateParam;
import com.github.huifer.full.shiro.model.res.ResultVO;
import com.github.huifer.full.shiro.service.PostService;
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
@RequestMapping("/post")
public class PostController {

  @Autowired
  private PostService postService;


  @PostMapping("/")
  public ResultVO create(@RequestBody PostCreateParam param) {
    return ResultVO.success(this.postService.create(param));
  }

  @PutMapping("/{id}")
  public ResultVO update(@RequestBody PostCreateParam param, @PathVariable("id") int id) {
    return ResultVO.success(this.postService.update(param, id));

  }

  @DeleteMapping("/")
  public ResultVO delete(@PathVariable("id") int id) {
    return ResultVO.success(this.postService.delete(id));

  }

  @GetMapping("/{id}")
  public ResultVO byId(@PathVariable("id") int id) {
    return ResultVO.success(this.postService.byId(id));
  }
}
