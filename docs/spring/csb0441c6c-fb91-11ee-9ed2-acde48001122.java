package com.github.huifer.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerRealm extends AuthorizingRealm {

  private static final Logger log = LoggerFactory.getLogger(CustomerRealm.class);

  /**
   * 授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
      PrincipalCollection principals) {
    return null;
  }

  /**
   * 认证
   *
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    // 认证方式: 从token对象中获取用户名和密码判断是否和db相同
    // 用户名
    Object principal = token.getPrincipal();
    // 密码
    Object credentials = token.getCredentials();
    log.info("username = [{}]", principal);
    // 简单的用户名验证
    if (String.valueOf(principal).equals("admin")) {
      SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,
          "admin", getName());
      return simpleAuthenticationInfo;
    }
    return null;
  }
}
