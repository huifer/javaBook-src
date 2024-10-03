package com.github.huifer.ctrpluginexample.repo;

import com.github.huifer.ctrpluginexample.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends CrudRepository<AppEntity, Long>  {

    // 现在我拥有一个 CrudRepository的实现类，我想要获取这个实现类的Class 我应该怎么操作

}
