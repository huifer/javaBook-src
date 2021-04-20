package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroApp;
import com.github.huifer.full.shiro.model.req.app.AppCreateParam;

public interface AppService {

  boolean create(AppCreateParam param);

  boolean update(AppCreateParam param, int id);

  boolean delete(int id);

  ShiroApp byId(int id);
}
