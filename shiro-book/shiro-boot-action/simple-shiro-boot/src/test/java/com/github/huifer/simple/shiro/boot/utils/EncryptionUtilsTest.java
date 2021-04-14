package com.github.huifer.simple.shiro.boot.utils;


import org.junit.Test;

public class EncryptionUtilsTest {


  @Test
  public void testRandomSalt() {
    assert EncryptionUtils.randomSalt(10).length() == 10;
  }


}