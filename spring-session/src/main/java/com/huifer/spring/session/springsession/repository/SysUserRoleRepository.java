package com.huifer.spring.session.springsession.repository;

import com.huifer.spring.session.springsession.entity.SysUserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : SysUserRoleRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Integer> {

    List<SysUserRole> findByUserId(Integer id);

    void deleteByUserId(Integer id);


}
