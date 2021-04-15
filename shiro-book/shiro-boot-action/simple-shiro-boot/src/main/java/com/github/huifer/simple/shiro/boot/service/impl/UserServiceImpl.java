package com.github.huifer.simple.shiro.boot.service.impl;

import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.repo.ShiroUserRepo;
import com.github.huifer.simple.shiro.boot.service.UserService;
import com.github.huifer.simple.shiro.boot.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ShiroUserRepo shiroUserRepo;

  @Override
  public boolean userCreate(UserCreateParam param) {
    String salt = EncryptionUtils.randomSalt(EncryptionUtils.SLAT_LEN);
    ShiroUserEntity shiroUserEntity = new ShiroUserEntity();
    shiroUserEntity.setUsername(param.getUsername());
    shiroUserEntity.setPassword(EncryptionUtils.genMD5Hash(param.getPassword(), salt));
    shiroUserEntity.setSalt(salt);
    ShiroUserEntity save = shiroUserRepo.save(shiroUserEntity);
    return save.getId() > 0;
  }
}
