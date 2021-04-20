package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroPost;
import com.github.huifer.full.shiro.entity.ShiroResource;
import com.github.huifer.full.shiro.model.req.post.PostCreateParam;
import com.github.huifer.full.shiro.model.req.resourece.ResourceCreateParam;

public interface ResourceService {
  boolean create(ResourceCreateParam param);

  boolean update(ResourceCreateParam param, int id);

  boolean delete(int id);

  ShiroResource byId(int id);

}
