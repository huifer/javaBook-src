package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroUserRepository extends JpaRepository<ShiroUser, Integer>,
    JpaSpecificationExecutor<ShiroUser> {

}