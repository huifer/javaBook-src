package com.github.huifer.simple.shiro.boot.utils;


import org.junit.Test;

public class EncryptionUtilsTest {


  @Test
  public void testRandomSalt() {
    assert EncryptionUtils.randomSalt(10).length() == 10;
  }

  @Test
  public void testMD5() {
    String admin = EncryptionUtils.genMD5Hash("admin", "123casad");
    System.out.println(admin);

    System.out.println(EncryptionUtils.genMD5Hash("admin", "1Ps7OiJPctRjd"));
  }

}