package com.huifer.idgen.my.service.conv;

import com.huifer.idgen.my.service.bean.Id;

/**
 * @author: wang
 * @description: IdConverter
 */
public interface IdConverter {

    /**
     * {@link Id 转换成longid}
     *
     * @param id id
     */
    long converter(Id id);

    /**
     * long id 转换成 {@link Id}
     *
     * @param id longId
     */
    Id converter(long id);

}
