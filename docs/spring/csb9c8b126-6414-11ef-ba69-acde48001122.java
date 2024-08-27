package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroPostDao;
import com.github.huifer.full.shiro.entity.ShiroPost;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.post.PostCreateParam;
import com.github.huifer.full.shiro.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private ShiroPostDao shiroPostDao;

  @Override
  public boolean create(PostCreateParam param) {
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("");
    }
    ShiroPost shiroPost = shiroPostDao.findByName(param.getName());
    if (shiroPost != null) {
      throw new ServerEx("");
    }
    ShiroPost ins = new ShiroPost();
    ins.setName(param.getName());
    return shiroPostDao.insert(ins) > 0;
  }

  @Override
  public boolean update(PostCreateParam param, int id) {
    ShiroPost up = this.shiroPostDao.selectById(id);

    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("");
    }
    if (!up.getName().equals(name)) {

      ShiroPost shiroPost = shiroPostDao.findByName(param.getName());
      if (shiroPost != null) {
        throw new ServerEx("");
      }
      up.setName(param.getName());
    }

    return this.shiroPostDao.updateById(up) > 0;
  }

  @Override
  public boolean delete(int id) {
    return this.shiroPostDao.deleteById(id) > 0;
  }

  @Override
  public ShiroPost byId(int id) {
    return this.shiroPostDao.selectById(id);
  }
}
