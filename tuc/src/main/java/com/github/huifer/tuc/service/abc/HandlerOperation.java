package com.github.huifer.tuc.service.abc;

import com.github.huifer.tuc.model.AbsData;

public interface HandlerOperation<T extends AbsData>
        extends BindOperation<T>,
        DataInsertAndUpdateOperation<T> {

    String ROLE_HANDLER_BEAN_NAME = "rbna";
    String MIDD_HANDLER_BEAN_NAME = "MIDD_HANDLER_BEAN_NAME";

    default void handler(T data) {
        if (hasBind(data)) {
            update(data);
        } else {
            Integer insert = insert(data);
            this.createBind(insert, data.getId());
        }
    }

    /**
     * 标记类型
     *
     * @return
     */
    Class<?> type();
}
