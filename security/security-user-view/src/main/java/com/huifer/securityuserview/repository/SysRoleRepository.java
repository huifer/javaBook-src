package com.huifer.securityuserview.repository;

import com.huifer.securityuserview.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

    @Override
    List<SysRole> findAll();
}
