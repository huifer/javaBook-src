package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroRoleBindResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroRoleBindResourceRepository extends
    JpaRepository<ShiroRoleBindResource, Integer>, JpaSpecificationExecutor<ShiroRoleBindResource> {

}