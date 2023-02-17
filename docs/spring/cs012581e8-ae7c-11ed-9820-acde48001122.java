package com.github.huifer.simple.shiro.boot.service.impl;

import com.github.huifer.simple.shiro.boot.entity.ShiroResourcesEntity;
import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import com.github.huifer.simple.shiro.boot.model.req.UserCreateParam;
import com.github.huifer.simple.shiro.boot.repo.ShiroResourcesRepo;
import com.github.huifer.simple.shiro.boot.repo.ShiroRoleRepo;
import com.github.huifer.simple.shiro.boot.repo.ShiroUserRepo;
import com.github.huifer.simple.shiro.boot.service.UserService;
import com.github.huifer.simple.shiro.boot.utils.EncryptionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ShiroUserRepo shiroUserRepo;

  @Autowired
  private ShiroRoleRepo shiroRoleRepo;
  @Autowired
  private ShiroResourcesRepo shiroResourcesRepo;

  @Override
  public ShiroUserEntity findByUsername(String username) {
    Optional<ShiroUserEntity> shiroUserEntityByUsername = this.shiroUserRepo
        .findShiroUserEntityByUsername(username);
    if (shiroUserEntityByUsername.isPresent()) {
      return shiroUserEntityByUsername.get();
    }
    return null ;
  }

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

  @Override
  public List<String> queryRolesForUsername(String username) {
    Optional<ShiroUserEntity> shiroUserEntityByUsername = this.shiroUserRepo
        .findShiroUserEntityByUsername(username);
    if (shiroUserEntityByUsername.isPresent()) {
      ShiroUserEntity shiroUserEntity = shiroUserEntityByUsername.get();
      return this.shiroRoleRepo.queryByUserId(shiroUserEntity.getId()).stream()
          .map(ShiroRoleEntity::getCode).collect(
              Collectors.toList());
    }
    return Collections.emptyList();
  }

  @Override
  public List<String> queryPermissionsForUsername(String username) {
    Optional<ShiroUserEntity> shiroUserEntityByUsername = this.shiroUserRepo
        .findShiroUserEntityByUsername(username);
    if (shiroUserEntityByUsername.isPresent()) {
      ShiroUserEntity shiroUserEntity = shiroUserEntityByUsername.get();
      return shiroResourcesRepo.queryByUserId(shiroUserEntity.getId()).stream()
          .map(ShiroResourcesEntity::getVal).collect(
              Collectors.toList());
    }
    return Collections.emptyList();
  }
}
