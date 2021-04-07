package com.github.huifer.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {

  public static void main(String[] args) {
    // 最小认证
    // 1. 创建安全管理器对象
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    // 2. 设置 realm 数据
    securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

    // 3. 设置安全工具类相关数据
    SecurityUtils.setSecurityManager(securityManager);

    // 4. 从安全工具类中获取 subject 对象
    Subject subject = SecurityUtils.getSubject();

    // 5. 创建令牌
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");

    // 6. 登陆
    // 认证状态
    boolean authenticated = subject.isAuthenticated();
    System.out.println("登录前的认证状态" + authenticated);
    subject.login(usernamePasswordToken);
    authenticated = subject.isAuthenticated();
    System.out.println("登录后的认证状态" + authenticated);

  }

}
