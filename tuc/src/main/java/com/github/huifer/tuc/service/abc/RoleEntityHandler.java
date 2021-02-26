package com.github.huifer.tuc.service.abc;

import com.github.huifer.tuc.model.RoleEntity;
import org.springframework.stereotype.Service;

@Service(HandlerOperation.ROLE_HANDLER_BEAN_NAME)
public class RoleEntityHandler implements HandlerOperation<com.github.huifer.tuc.model.RoleEntity> {

    // 关于 RoleEntity 相关的Mapper

    @Override
    public boolean hasBind(RoleEntity roleEntity) {
        return false;
    }

    @Override
    public void createBind(Integer otherId, Integer myId) {

    }

    @Override
    public Integer insert(RoleEntity data) {
        return null;
    }

    @Override
    public void update(RoleEntity data) {

    }

    @Override
    public Class<?> type() {
        return RoleEntity.class;
    }
}
