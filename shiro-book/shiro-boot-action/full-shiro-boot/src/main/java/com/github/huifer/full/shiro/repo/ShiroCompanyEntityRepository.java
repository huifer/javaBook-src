package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroCompanyEntityRepository extends JpaRepository<ShiroCompanyEntity, Integer>,
    JpaSpecificationExecutor<ShiroCompanyEntity> {

}