package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroPostBindRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroPostBindRoleEntityRepository extends
    JpaRepository<ShiroPostBindRoleEntity, Integer>,
    JpaSpecificationExecutor<ShiroPostBindRoleEntity> {

}