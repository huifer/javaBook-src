package com.github.huifer.simple.shiro.boot.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class EncryptionUtils {

  public static String randomSalt(int len) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

  return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(len)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }


}
