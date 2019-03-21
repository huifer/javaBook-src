package com.huifer.security.repository;

import com.huifer.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
public interface UserRepority extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

}
