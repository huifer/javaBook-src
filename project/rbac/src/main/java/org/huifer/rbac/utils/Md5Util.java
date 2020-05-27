package org.huifer.rbac.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    private Md5Util() {
        throw new IllegalArgumentException("工具类");
    }

    public static String MD5(String codecStr) {
        return DigestUtils.md5Hex(codecStr);
    }
}
