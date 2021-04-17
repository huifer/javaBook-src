package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroPostEntityRepository extends JpaRepository<ShiroPostEntity, Integer>,
    JpaSpecificationExecutor<ShiroPostEntity> {

}