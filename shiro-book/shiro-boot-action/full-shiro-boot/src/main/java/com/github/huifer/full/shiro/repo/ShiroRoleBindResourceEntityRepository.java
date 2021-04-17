package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroRoleBindResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroRoleBindResourceEntityRepository extends
    JpaRepository<ShiroRoleBindResourceEntity, Integer>,
    JpaSpecificationExecutor<ShiroRoleBindResourceEntity> {

}