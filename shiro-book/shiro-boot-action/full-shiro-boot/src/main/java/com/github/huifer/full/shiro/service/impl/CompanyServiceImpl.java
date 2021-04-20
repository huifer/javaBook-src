package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.dao.ShiroCompanyDao;
import com.github.huifer.full.shiro.entity.ShiroCompany;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.company.CompanyCreateParam;
import com.github.huifer.full.shiro.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private ShiroCompanyDao companyDao;

  @Override
  public boolean create(CompanyCreateParam param) {
    ShiroCompany shiroCompany = getShiroCompany(param);

    return companyDao.insert(shiroCompany) > 0;
  }

  private ShiroCompany getShiroCompany(CompanyCreateParam param) {
    ShiroCompany shiroCompany = new ShiroCompany();
    shiroCompany.setAddress(param.getAddress());
    shiroCompany.setName(param.getName());
    return shiroCompany;
  }

  @Override
  public boolean update(CompanyCreateParam param, int id) {
    ShiroCompany db = companyDao.selectById(id);
    if (db == null) {
      throw new ServerEx("当前id对应的企业不存在");
    }
    ShiroCompany shiroCompany = getShiroCompany(param);
    return this.companyDao.updateById(shiroCompany) > 0;
  }

  @Override
  public boolean delete(int id) {
    return this.companyDao.deleteById(id) > 0;
  }

  @Override
  public ShiroCompany byId(int id) {
    return this.companyDao.selectById(id);
  }
}
