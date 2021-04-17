package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroRoleEntityRepository extends JpaRepository<ShiroRoleEntity, Integer>,
    JpaSpecificationExecutor<ShiroRoleEntity> {

}