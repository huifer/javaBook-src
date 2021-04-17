package com.github.huifer.full.shiro.service.impl;

import com.github.huifer.full.shiro.entity.ShiroUserEntity;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.user.UserCreateParam;
import com.github.huifer.full.shiro.repo.ShiroUserEntityRepository;
import com.github.huifer.full.shiro.service.UserService;
import com.github.huifer.full.shiro.utils.EncryptionUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ShiroUserEntityRepository userEntityRepository;

  @Override
  public ShiroUserEntity create(
      UserCreateParam param) {

    Optional<ShiroUserEntity> shiroUserEntityByLoginName = userEntityRepository
        .findShiroUserEntityByLoginName(param.getLoginName());
    if (!shiroUserEntityByLoginName.isPresent()) {

      ShiroUserEntity shiroUserEntity = new ShiroUserEntity();
      shiroUserEntity.setLoginName(param.getLoginName());
      shiroUserEntity.setUsername(param.getUsername());
      String salt = EncryptionUtils.randomSalt(EncryptionUtils.SLAT_LEN);
      shiroUserEntity.setPassword(EncryptionUtils.genMD5Hash(param.getPassword(), salt));
      shiroUserEntity.setSalt(salt);
      shiroUserEntity.setEmail(param.getEmail());
      shiroUserEntity.setGender(param.getGender());
      return userEntityRepository.save(shiroUserEntity);
    }
    throw new ServerEx("登录名已存在");
  }


  @Override
  public ShiroUserEntity update(UserCreateParam param, int id) {
    Optional<ShiroUserEntity> byId = userEntityRepository.findById(id);
    if (byId.isPresent()) {
      ShiroUserEntity shiroUserEntity = byId.get();
      shiroUserEntity.setUsername(param.getUsername());
      String salt = EncryptionUtils.randomSalt(EncryptionUtils.SLAT_LEN);
      shiroUserEntity.setPassword(EncryptionUtils.genMD5Hash(param.getPassword(), salt));
      shiroUserEntity.setSalt(salt);
      shiroUserEntity.setEmail(param.getEmail());
      shiroUserEntity.setGender(param.getGender());
      return userEntityRepository.save(shiroUserEntity);
    }
    throw new ServerEx("当前id对应用户不存在");

  }

  @Override
  public boolean delete(int id) {
    return false;
  }
}
