package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiroRoleRepo extends JpaRepository<ShiroRoleEntity, Integer> {

  @Query(value = "select * from shiro_role where id in (select role_id from user_bind_role where user_id = :user_id)",
  nativeQuery = true)
  List<ShiroRoleEntity> queryByUserId(@Param("user_id") Integer userId);
}
