package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroRoleEntity;
import com.github.huifer.simple.shiro.boot.entity.UserBindResourceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBindRoleRepo extends CrudRepository<UserBindResourceEntity, Integer> {




}
