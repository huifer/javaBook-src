package com.huifer.idgen.my.service.util;

import java.util.Arrays;

/**
 * @author: wang
 * @description:
 */
public class CommonUtils {
    private static final String[] SWITCH_ON = {"ON", "TRUE"};
    private static final String[] SWITCH_OFF = {"OFF", "FALSE"};

    private CommonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isOn(String sw) {
        return Arrays.asList(SWITCH_ON).contains(sw);
    }

    public static boolean isOff(String sw) {
        return Arrays.asList(SWITCH_OFF).contains(sw);
    }

    public static boolean isPropKeyOff(String key) {
        String property = System.getProperty(key);
        return Arrays.asList(SWITCH_OFF).contains(property);
    }

    public static boolean isPropKeyOn(String key) {
        String property = System.getProperty(key);
        return Arrays.asList(SWITCH_ON).contains(property);
    }
}