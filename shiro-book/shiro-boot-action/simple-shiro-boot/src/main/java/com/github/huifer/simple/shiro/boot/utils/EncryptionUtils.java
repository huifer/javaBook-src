package com.github.huifer.simple.shiro.boot.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.apache.shiro.crypto.hash.Md5Hash;

public class EncryptionUtils {

  /**
   * 生成随机字符串
   * @param len 字符串长度
   * @return 随机字符串
   */
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

  /**
   * Hash 散列执行次数
   */
  public static final int HASH_ITERATIONS = 1024;
  public static final int SLAT_LEN = 13;

  /**
   * 生成密码的MD5加密
   * @param password 密码
   * @return 加密的密码
   */
  public static String genMD5Hash(String password) {
    Md5Hash md5Hash = new Md5Hash(password, randomSalt(SLAT_LEN), HASH_ITERATIONS);
    return md5Hash.toHex();
  }

  /**
   * 根据盐+密码生成MDK5加密的密码
   * @param password 密码
   * @param salt 盐
   * @return 加密的密码
   */
  public static String genMD5Hash(String password, String salt) {
    Md5Hash md5Hash = new Md5Hash(password, salt, HASH_ITERATIONS);
    return md5Hash.toHex();
  }


}
