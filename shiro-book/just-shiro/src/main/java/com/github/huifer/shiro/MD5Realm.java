package com.github.huifer.shiro;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Realm extends AuthorizingRealm {

  private static final Logger log = LoggerFactory.getLogger(MD5Realm.class);
  Map<String, String> userMap = new HashMap<>(16);

  public void register(String username, String password) {
    Md5Hash md5Hash = new Md5Hash(password, "salt", 1024);
    userMap.put(username, md5Hash.toHex());
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
      PrincipalCollection principals) {

    // 主要的身份信息
    Object primaryPrincipal = principals.getPrimaryPrincipal();
    log.info("主身份是 =[{}]", primaryPrincipal);

    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    simpleAuthorizationInfo.addStringPermission("user:*:*");
    return simpleAuthorizationInfo;
  }

  private SimpleAuthorizationInfo withRole() {
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    simpleAuthorizationInfo.addRole("admin");
    simpleAuthorizationInfo.addRole("test");
    return simpleAuthorizationInfo;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(
      AuthenticationToken token) throws AuthenticationException {
    Object principal = token.getPrincipal();
    String password = this.userMap.get(String.valueOf(principal));
    if (password != null) {
      return new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes("salt"),
          getName()
      );
    }
    return null;
  }
}
