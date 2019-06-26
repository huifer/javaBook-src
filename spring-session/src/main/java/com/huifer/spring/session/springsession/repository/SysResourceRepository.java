package com.huifer.spring.session.springsession.repository;

import com.huifer.spring.session.springsession.entity.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title : SysResourceRepository </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Repository
public interface SysResourceRepository extends JpaRepository<SysResource, Integer> {

}
