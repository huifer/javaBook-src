package com.huifer.webflux.util;

import com.huifer.webflux.exception.StudentException;

import java.util.Arrays;

/**
 * 描述:
 * 校验姓名
 *
 * @author huifer
 * @date 2019-06-02
 */
public class NameValidateUtil {


    public static final String[] INVALIDE_NAMES = {"ADMIN", "ROOT"};

    public static void validateName(String name) {

        Arrays.stream(INVALIDE_NAMES).filter(s -> name.equalsIgnoreCase(s))
                .findAny()
                .ifPresent(s -> {
                    throw new StudentException("name 非法名词", s, "非法名词");
                });

        ;

    }
}
