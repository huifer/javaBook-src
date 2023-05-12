package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroCompany;
import com.github.huifer.full.shiro.entity.ShiroPost;
import com.github.huifer.full.shiro.model.req.company.CompanyCreateParam;
import com.github.huifer.full.shiro.model.req.post.PostCreateParam;

public interface PostService {

  boolean create(PostCreateParam param);

  boolean update(PostCreateParam param, int id);

  boolean delete(int id);

  ShiroPost byId(int id);
}
