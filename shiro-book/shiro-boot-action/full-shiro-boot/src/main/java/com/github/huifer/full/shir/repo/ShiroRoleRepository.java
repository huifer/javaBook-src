package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroRoleRepository extends JpaRepository<ShiroRole, Integer>,
    JpaSpecificationExecutor<ShiroRole> {

}