package com.huifer.securityuserview.repository;

import com.huifer.securityuserview.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByUsername(String username);
}
