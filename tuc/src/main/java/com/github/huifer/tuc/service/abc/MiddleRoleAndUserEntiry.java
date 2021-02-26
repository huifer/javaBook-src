package com.github.huifer.tuc.service.abc;

import static com.github.huifer.tuc.service.abc.HandlerOperation.MIDD_HANDLER_BEAN_NAME;
import com.github.huifer.tuc.model.AbsData;
import com.github.huifer.tuc.model.MiddleRoleAndUserEntity;
import org.springframework.stereotype.Service;

@Service(MIDD_HANDLER_BEAN_NAME)
public class MiddleRoleAndUserEntiry<T extends AbsData> implements
        HandlerOperation<MiddleRoleAndUserEntity> {

    @Override
    public boolean hasBind(MiddleRoleAndUserEntity middleRoleAndUserEntity) {
        return false;
    }

    @Override
    public void createBind(Integer otherId, Integer myId) {

    }

    @Override
    public Integer insert(MiddleRoleAndUserEntity data) {
        return null;
    }

    @Override
    public void update(MiddleRoleAndUserEntity data) {

    }

    @Override
    public Class<?> type() {
        return MiddleRoleAndUserEntity.class;
    }
}
