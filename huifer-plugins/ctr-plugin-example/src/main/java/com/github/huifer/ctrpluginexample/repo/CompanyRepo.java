package com.github.huifer.ctrpluginexample.repo;

import com.github.huifer.ctrpluginexample.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends CrudRepository<CompanyEntity, Long> {

}
