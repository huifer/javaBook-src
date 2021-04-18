package com.github.huifer.full.shiro.repo;

import com.github.huifer.full.shiro.entity.ShiroUserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShiroUserEntityRepository extends JpaRepository<ShiroUserEntity, Integer>,
    JpaSpecificationExecutor<ShiroUserEntity> {

  Optional<ShiroUserEntity> findShiroUserEntityByLoginName(String loginName);

}