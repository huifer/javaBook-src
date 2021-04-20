package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroRoleDao;
import com.github.huifer.full.shiro.entity.ShiroRole;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.role.RoleCreateParam;
import com.github.huifer.full.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private ShiroRoleDao shiroRoleDao;

  @Override
  public boolean create(RoleCreateParam param) {
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("");
    }
    String code = param.getCode();
    if (!StringUtils.hasText(code)) {
      throw new ServerEx("");
    }

    ShiroRole byNameAndCode = this.shiroRoleDao.findByNameAndCode(name, code);
    if (byNameAndCode != null) {
      throw new ServerEx();
    }

    ShiroRole ins = new ShiroRole();
    ins.setCode(code);
    ins.setName(name);

    return this.shiroRoleDao.insert(ins) > 0;
  }

  @Override
  public boolean update(RoleCreateParam param, int id) {
    ShiroRole up = this.shiroRoleDao.selectById(id);
    String name = param.getName();
    if (!StringUtils.hasText(name)) {
      throw new ServerEx("");
    }
    String code = param.getCode();
    if (!StringUtils.hasText(code)) {
      throw new ServerEx("");
    }
    ShiroRole byNameAndCode;
    if (!up.getCode().equals(code)) {

      byNameAndCode = this.shiroRoleDao.findByNameAndCode(name, code);
      if (byNameAndCode != null) {
        throw new ServerEx();
      }
      up.setCode(code);
    }
    if (!up.getName().equals(name)) {
      byNameAndCode = this.shiroRoleDao.findByNameAndCode(name, code);
      if (byNameAndCode != null) {
        throw new ServerEx();
      }
      up.setName(name);
    }
    return this.shiroRoleDao.updateById(up) > 0;
  }

  @Override
  public boolean delete(int id) {
    return this.shiroRoleDao.deleteById(id) > 0;
  }

  @Override
  public ShiroRole byId(int id) {
    return this.shiroRoleDao.selectById(id);
  }
}
