package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroPost;
import com.github.huifer.full.shiro.entity.ShiroRole;
import com.github.huifer.full.shiro.model.req.post.PostCreateParam;
import com.github.huifer.full.shiro.model.req.role.RoleCreateParam;

public interface RoleService {

  boolean create(RoleCreateParam param);

  boolean update(RoleCreateParam param, int id);

  boolean delete(int id);

  ShiroRole byId(int id);
}
