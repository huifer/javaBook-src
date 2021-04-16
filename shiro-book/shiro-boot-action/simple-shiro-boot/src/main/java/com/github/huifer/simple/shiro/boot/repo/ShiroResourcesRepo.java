package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroResourcesEntity;
import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiroResourcesRepo extends CrudRepository<ShiroResourcesEntity, Integer> {
  @Query(
      value = "select * from shiro_resources where id in (select resource_id from user_bind_resource where user_id = :user_id)",
      nativeQuery = true)
  List<ShiroResourcesEntity> queryByUserId(@Param("user_id") Integer userId);

}
