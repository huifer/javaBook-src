package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroAppDao;
import com.github.huifer.full.shiro.entity.ShiroApp;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.app.AppCreateParam;
import com.github.huifer.full.shiro.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AppServiceImpl implements
    AppService {

  @Autowired
  private ShiroAppDao shiroAppDao;

  @Override
  public boolean create(AppCreateParam param) {
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("应用名称必填");
    }
    ShiroApp shiroApp = this.shiroAppDao.findByName(name);
    if (shiroApp != null) {
      throw new ServerEx("应用名称已存在");
    }

    ShiroApp ins = new ShiroApp();
    ins.setName(name);
    return this.shiroAppDao.insert(ins) > 0;
  }

  @Override
  public boolean update(AppCreateParam param, int id) {
    ShiroApp byId = this.shiroAppDao.selectById(id);
    if (byId != null) {
      String name = param.getName();
      if (!StringUtils.hasText(name)) {
        throw new ServerEx("应用名称必填");
      }
      if (!byId.getName().equals(name)) {
        ShiroApp byName = this.shiroAppDao.findByName(name);
        if (byName != null) {
          throw new ServerEx("应用名称已存在");
        }
        byId.setName(name);
      }

      if (byId != null) {
        return this.shiroAppDao.updateById(byId) > 0;
      }
    }

    throw new ServerEx("当前id无法插叙对应应用");
  }

  @Override
  public boolean delete(int id) {
    return false;
  }

  @Override
  public ShiroApp byId(int id) {
    return this.shiroAppDao.selectById(id);

  }
}
