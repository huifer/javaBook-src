package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroDeptDao;
import com.github.huifer.full.shiro.entity.ShiroDept;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.dept.DeptCreateParam;
import com.github.huifer.full.shiro.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DeptServiceImpl implements DeptService {

  @Autowired
  private ShiroDeptDao shiroDeptDao;

  @Override
  public boolean create(DeptCreateParam param) {
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("");
    }
    Integer companyId = param.getCompanyId();
    if (companyId == null) {
      throw new ServerEx("");
    }
    ShiroDept byName = this.shiroDeptDao.findByName(name);
    if (byName != null) {
      throw new ServerEx("");
    }
    ShiroDept ins = new ShiroDept();
    ins.setCompanyId(param.getCompanyId());
    ins.setName(param.getName());
    ins.setMainUser(param.getMainUser());
    ins.setPid(param.getPid());

    return this.shiroDeptDao.insert(ins) > 0;
  }

  @Override
  public boolean update(DeptCreateParam param, int id) {
    ShiroDept shiroDept = this.shiroDeptDao.selectById(id);
    if (shiroDept != null) {

      String name = param.getName();
      if (!StringUtils.hasText(name)) {
        throw new ServerEx("");
      }
      Integer companyId = param.getCompanyId();
      if (companyId == null) {
        throw new ServerEx("");
      }
      if (!shiroDept.getName().equals(name)) {

        ShiroDept byName = this.shiroDeptDao.findByName(name);
        if (byName != null) {
          throw new ServerEx("");
        }
        shiroDept.setName(param.getName());
      }
      shiroDept.setMainUser(param.getMainUser());
      shiroDept.setPid(param.getPid());
      return shiroDeptDao.updateById(shiroDept) > 0;
    }
    return false;
  }

  @Override
  public boolean delete(int id) {
    return this.shiroDeptDao.deleteById(id) > 0;
  }

  @Override
  public ShiroDept byId(int id) {
    return this.shiroDeptDao.selectById(id);
  }
}
