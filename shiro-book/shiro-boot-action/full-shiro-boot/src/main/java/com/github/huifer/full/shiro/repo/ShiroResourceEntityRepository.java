package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroResourceEntityRepository extends JpaRepository<ShiroResourceEntity, Integer>,
    JpaSpecificationExecutor<ShiroResourceEntity> {

}