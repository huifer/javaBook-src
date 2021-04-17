package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroDeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroDeptEntityRepository extends JpaRepository<ShiroDeptEntity, Integer>,
    JpaSpecificationExecutor<ShiroDeptEntity> {

}