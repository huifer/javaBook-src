package com.huifer.spring.session.springsession.repository;

import com.huifer.spring.session.springsession.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : SysRoleRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

    Page<SysRole> findAll(Specification<SysRole> sysRoleSpecification, Pageable pageable);


}
