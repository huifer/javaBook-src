package com.github.huifer.ctrpluginexample.ctr;

import com.github.huifer.ctrpluginexample.entity.AppEntity;
import com.github.huifer.ctrpluginexample.runner.DemoRunner;
import com.github.huifer.ctrpluginexample.runner.DemoRunner.CrudRepoCache;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dac")
public class DemoController {

    Gson gson = new Gson();
    @PostMapping("/two/{table}")
    public ResponseEntity<Object> two(
            @PathVariable(value = "table") String table,
            @RequestBody Object data
    ) throws IllegalAccessException, InstantiationException {

        CrudRepoCache crudRepoCache = DemoRunner.crudRepositoryMap.get(table);
        // 转换成注解上的泛型
        Object param = gson.fromJson(gson.toJson(data), crudRepoCache.getInsertedClass());
        Object o = crudRepoCache.getInsertOrUpdateConvertBean().fromInsType(param);
        Object save = crudRepoCache.getCrudRepository().save(o);
        return ResponseEntity.ok(save);
    }


    @PostMapping("/{table}")
    public ResponseEntity<Object> op(
            @PathVariable(value = "table") String table,
            @RequestBody Object data
    ) throws IllegalAccessException, InstantiationException {

        CrudRepoCache crudRepoCache = DemoRunner.crudRepositoryMap.get(table);
        // 转换成注解上的泛型
        Object o = gson.fromJson(gson.toJson(data), crudRepoCache.getInsertedClass());
        // 注解上的泛型转换成数据库泛型
        Class<?> self = crudRepoCache.getSelf();
        Object insData = gson.fromJson(gson.toJson(o), self);
        Object save = crudRepoCache.getCrudRepository().save(insData);
        return ResponseEntity.ok(save);
    }
}
