package com.huifer.idgen.my.service.factory;

import com.huifer.idgen.my.service.bean.IdMeta;
import com.huifer.idgen.my.service.bean.enums.IdType;

/**
 * @author: wang
 * @description:
 */
public class IdMetaFactory {

    private static IdMeta idMetaOne = new IdMeta((byte) 10, (byte) 20, (byte) 30, (byte) 2, (byte) 1, (byte) 1);

    private static IdMeta idMetaTwo = new IdMeta((byte) 10, (byte) 10, (byte) 40, (byte) 2, (byte) 1, (byte) 1);

    private IdMetaFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static IdMeta getIdMeta(IdType type) {
        if (IdType.TYPE_ONE.equals(type)) {
            return idMetaOne;
        } else if (IdType.TYPE_TWO.equals(type)) {
            return idMetaTwo;
        }
        return null;
    }
}