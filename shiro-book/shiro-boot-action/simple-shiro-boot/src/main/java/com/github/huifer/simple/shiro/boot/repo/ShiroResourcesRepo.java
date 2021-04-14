package com.github.huifer.simple.shiro.boot.repo;

import com.github.huifer.simple.shiro.boot.entity.ShiroResourcesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiroResourcesRepo extends CrudRepository<ShiroResourcesEntity, Integer> {


}
