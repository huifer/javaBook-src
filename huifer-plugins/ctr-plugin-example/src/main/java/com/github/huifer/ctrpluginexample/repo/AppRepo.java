package com.github.huifer.ctrpluginexample.repo;

import com.github.huifer.ctrpluginexample.entity.AppEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends CrudRepository<AppEntity, Long> {


}
