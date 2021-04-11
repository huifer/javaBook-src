package com.github.huifer.shiro;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MD5Realm extends AuthorizingRealm {

  Map<String, String> userMap = new HashMap<>(16);

  public void register(String username, String password) {
    Md5Hash md5Hash = new Md5Hash(password, "salt", 1024);
    userMap.put(username, md5Hash.toHex());
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
      PrincipalCollection principals) {
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(
      AuthenticationToken token) throws AuthenticationException {
    Object principal = token.getPrincipal();
    String password = this.userMap.get(String.valueOf(principal));
    if (password != null) {
      return new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes("salt"), getName());
    }
    return null;
  }
}
