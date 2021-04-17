package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroUserExtendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroUserExtendEntityRepository extends
    JpaRepository<ShiroUserExtendEntity, Integer>, JpaSpecificationExecutor<ShiroUserExtendEntity> {

}