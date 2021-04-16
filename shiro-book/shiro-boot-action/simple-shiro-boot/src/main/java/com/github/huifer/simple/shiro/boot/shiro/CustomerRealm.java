package com.github.huifer.simple.shiro.boot.shiro;

import com.github.huifer.simple.shiro.boot.entity.ShiroUserEntity;
import com.github.huifer.simple.shiro.boot.service.UserService;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRealm extends AuthorizingRealm {


  @Autowired
  private UserService userService;


  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(
      AuthenticationToken token) throws AuthenticationException {
    if (token instanceof UsernamePasswordToken
    ) {

      String username = ((UsernamePasswordToken) token).getUsername();
      String pwd = new String(((UsernamePasswordToken) token).getPassword());

      if (username == null) {
        throw new RuntimeException("用户名称不能为空");
      }
      if (pwd == null) {
        throw new RuntimeException("密码不能为空");
      }

      ShiroUserEntity byUsername = this.userService.findByUsername(username);
      if (byUsername != null) {

        String salt = byUsername.getSalt();
        return new SimpleAuthenticationInfo(byUsername.getUsername(), byUsername.getPassword(),
            ByteSource.Util.bytes(salt), getName());
      }
    }
    return null;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(
      PrincipalCollection principals) {
    Object primaryPrincipal = principals.getPrimaryPrincipal();
    if (primaryPrincipal == null) {
      throw new RuntimeException("ex");
    }
    List<String> permissions = this.userService
        .queryPermissionsForUsername(String.valueOf(principals));
    List<String> roles = this.userService.queryRolesForUsername(String.valueOf(principals));
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    simpleAuthorizationInfo.addStringPermissions(permissions);
    simpleAuthorizationInfo.addRoles(roles);
    return simpleAuthorizationInfo;
  }
}
