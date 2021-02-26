package com.github.huifer.tuc.service.abc;

import com.github.huifer.tuc.model.AbsData;

public interface BindOperation<T extends AbsData> {

    boolean hasBind(T t);

    void createBind(Integer otherId, Integer myId);
}
