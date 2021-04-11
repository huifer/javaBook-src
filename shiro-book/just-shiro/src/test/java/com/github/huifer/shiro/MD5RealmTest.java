package com.github.huifer.shiro;

import java.nio.charset.StandardCharsets;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class MD5RealmTest {

  @Test
  public void testMD5Realm() {
    DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
    MD5Realm realm = new MD5Realm();

    // 加密算法
    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
    // 散列次数
    credentialsMatcher.setHashIterations(1024);
    realm.setCredentialsMatcher(credentialsMatcher);


    defaultSecurityManager.setRealm(realm);
    SecurityUtils.setSecurityManager(defaultSecurityManager);
    Subject subject = SecurityUtils.getSubject();

    realm.register("admin", "admin");
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");
    subject.login(usernamePasswordToken);
  }

  @Test
  public void md5HashTest() {
    Md5Hash md5Hash = new Md5Hash();
    md5Hash.setBytes("password".getBytes(StandardCharsets.UTF_8));
    String md5Hex = md5Hash.toHex();
    System.out.println(md5Hex);
    Md5Hash md5Hash2 = new Md5Hash("password", "salt", 1024);
    String hex = md5Hash2.toHex();

    System.out.println(hex);
  }
}