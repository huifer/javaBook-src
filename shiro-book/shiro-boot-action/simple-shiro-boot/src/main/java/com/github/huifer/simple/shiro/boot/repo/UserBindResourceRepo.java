package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.UserBindResourceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBindResourceRepo extends CrudRepository<UserBindResourceEntity, Integer> {

}
