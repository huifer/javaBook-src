package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroPostBindRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroPostBindRoleRepository extends JpaRepository<ShiroPostBindRole, Integer>,
    JpaSpecificationExecutor<ShiroPostBindRole> {

}