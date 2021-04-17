package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroUserExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroUserExtendRepository extends JpaRepository<ShiroUserExtend, Integer>,
    JpaSpecificationExecutor<ShiroUserExtend> {

}