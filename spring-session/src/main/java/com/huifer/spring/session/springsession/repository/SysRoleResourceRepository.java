package com.huifer.spring.session.springsession.repository;

import com.huifer.spring.session.springsession.entity.SysRoleResource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : SysRoleResourceRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Repository
public interface SysRoleResourceRepository extends JpaRepository<SysRoleResource, Integer> {

    List<SysRoleResource> findByRoleId(Integer roleId);

    List<SysRoleResource> findByRoleId(List<Integer> roleIds);

    void deleteByRoleId(Integer id);

}
