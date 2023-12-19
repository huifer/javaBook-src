package com.github.huifer.full.shiro.service;

import com.github.huifer.full.shiro.entity.ShiroDept;
import com.github.huifer.full.shiro.model.req.dept.DeptCreateParam;

public interface DeptService {

  boolean create(DeptCreateParam param);

  boolean update(DeptCreateParam param, int id);

  boolean delete(int id);

  ShiroDept byId(int id);
}
