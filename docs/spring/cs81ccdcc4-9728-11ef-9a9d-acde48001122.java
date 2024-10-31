package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroResourceDao;
import com.github.huifer.full.shiro.entity.ShiroResource;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.resourece.ResourceCreateParam;
import com.github.huifer.full.shiro.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ShiroResourceDao shiroResourceDao;

  @Override
  public boolean create(ResourceCreateParam param) {
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx();
    }
    Integer appId = param.getAppId();
    if (appId == null) {
      throw new ServerEx();
    }
    Integer type = param.getType();
    if (type == null) {
      throw new ServerEx();
    }
    ShiroResource shiroResource = this.shiroResourceDao
        .findByNameAndAppIdAndType(name, appId, type);
    if (shiroResource != null) {
      throw new ServerEx();
    }
    ShiroResource ins = new ShiroResource();
    ins.setType(type);
    ins.setName(name);
    ins.setAppId(appId);

    return this.shiroResourceDao.insert(ins) > 0;
  }

  @Override
  public boolean update(ResourceCreateParam param, int id) {
    ShiroResource shiroResource = this.shiroResourceDao.selectById(id);
    if (shiroResource != null) {
      String name = param.getName();
      Integer type = param.getType();
      ShiroResource byNameAndAppIdAndType;

      if (!shiroResource.getType().equals(type)) {
        byNameAndAppIdAndType = this.shiroResourceDao
            .findByNameAndAppIdAndType(name, shiroResource.getAppId(), type);

        if (byNameAndAppIdAndType != null) {
          throw new ServerEx();
        }
        shiroResource.setType(type);
      }

      if (!shiroResource.getName().equals(name)) {
        byNameAndAppIdAndType = this.shiroResourceDao
            .findByNameAndAppIdAndType(name, shiroResource.getAppId(), type);

        if (byNameAndAppIdAndType != null) {
          throw new ServerEx();
        }
        shiroResource.setName(name);
      }

      return this.shiroResourceDao.updateById(shiroResource) > 0;
    }
    return false;
  }

  @Override
  public boolean delete(int id) {
    return this.shiroResourceDao.deleteById(id) > 0;
  }

  @Override
  public ShiroResource byId(int id) {
    return this.shiroResourceDao.selectById(id);
  }
}
