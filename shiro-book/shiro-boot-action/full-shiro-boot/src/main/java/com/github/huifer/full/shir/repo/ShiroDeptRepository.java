package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroDeptRepository extends JpaRepository<ShiroDept, Integer>,
    JpaSpecificationExecutor<ShiroDept> {

}