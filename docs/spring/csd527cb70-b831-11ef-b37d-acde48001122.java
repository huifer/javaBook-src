package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiroUserRepo extends CrudRepository<ShiroUserEntity, Integer> {
  Optional<ShiroUserEntity> findShiroUserEntityByUsername(String username);
}
