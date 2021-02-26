package com.github.huifer.tuc.service;

import com.github.huifer.tuc.model.AbsData;
import com.github.huifer.tuc.model.MiddleRoleAndUserEntity;
import com.github.huifer.tuc.model.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl extends DemoService {

    // mapper 数量等价于 clazz 的数量

    @Override
    boolean hasBind(Object data, Class<?> clazz) {
        if (clazz.equals(RoleEntity.class)) {

        } else if (clazz.equals(MiddleRoleAndUserEntity.class)) {

        }
        return false;
    }

    @Override
    void createBind(Integer otherId, Integer myId, Class<?> clazz) {
        if (clazz.equals(RoleEntity.class)) {

        } else if (clazz.equals(MiddleRoleAndUserEntity.class)) {

        }
    }

    @Override
    public void handler(AbsData o, Class<?> clazz) {
        super.handler(o, clazz);
    }

    @Override
    Integer insert(Object data, Class<?> clazz) {
        if (clazz.equals(RoleEntity.class)) {

        } else if (clazz.equals(MiddleRoleAndUserEntity.class)) {

        }
        return null;
    }

    @Override
    void update(Object data, Class<?> clazz) {
        if (clazz.equals(RoleEntity.class)) {

        } else if (clazz.equals(MiddleRoleAndUserEntity.class)) {

        }
    }
}
