package com.github.huifer.shiro;

import static org.junit.Assert.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomerRealmTest {

  @Test
  public void testCustomerRealm(){
    DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
    defaultSecurityManager.setRealm(new CustomerRealm());
    SecurityUtils.setSecurityManager(defaultSecurityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin2123");
    subject.login(usernamePasswordToken);

  }

}