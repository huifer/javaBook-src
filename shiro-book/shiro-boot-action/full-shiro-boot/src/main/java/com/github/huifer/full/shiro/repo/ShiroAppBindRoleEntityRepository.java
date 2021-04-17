package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroAppBindRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroAppBindRoleEntityRepository extends
    JpaRepository<ShiroAppBindRoleEntity, Integer>,
    JpaSpecificationExecutor<ShiroAppBindRoleEntity> {

}