package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroResourceRepository extends JpaRepository<ShiroResource, Integer>,
    JpaSpecificationExecutor<ShiroResource> {

}