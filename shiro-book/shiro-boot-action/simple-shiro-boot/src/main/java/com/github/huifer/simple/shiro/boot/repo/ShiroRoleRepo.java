package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiroRoleRepo extends CrudRepository<ShiroRoleEntity, Integer> {


}
