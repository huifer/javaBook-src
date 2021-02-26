package com.github.huifer.tuc.service.abc;

import com.github.huifer.tuc.model.AbsData;

public interface DataInsertAndUpdateOperation<T extends AbsData> {

    Integer insert(T data);

    void update(T data);
}
