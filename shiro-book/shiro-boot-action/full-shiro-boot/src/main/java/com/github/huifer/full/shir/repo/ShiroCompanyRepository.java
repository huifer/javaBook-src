package com.github.huifer.full.shir.repo;

import com.github.huifer.full.shir.entity.ShiroCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiroCompanyRepository extends JpaRepository<ShiroCompany, Integer>,
    JpaSpecificationExecutor<ShiroCompany> {

}