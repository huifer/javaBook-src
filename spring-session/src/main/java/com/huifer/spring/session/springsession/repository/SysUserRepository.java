package com.huifer.spring.session.springsession.repository;

import com.huifer.spring.session.springsession.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : SysUserRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByAccount(String account);

    Page<SysUser> findAll(Specification<SysUser> sysUserSpecification, Pageable pageable);

}
