package com.github.huifer.full.shiro.config;

import com.github.huifer.full.shiro.CustomerRealm;
import com.github.huifer.full.shiro.utils.EncryptionUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro配置类 :
 * <ol>
 *   <li>创建Shiro的过滤器 {@link org.apache.shiro.web.servlet.ShiroFilter}</li>
 *   <li>创建全局安全管理器 {@link
 *  * org.apache.shiro.mgt.SecurityManager}</li>
 *   <li>创建自定义 realm.</li>
 * </ol>
 */
@Configuration
public class ShiroConfig {

  @Bean
  public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    defaultAdvisorAutoProxyCreator.setUsePrefix(true);

    return defaultAdvisorAutoProxyCreator;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(
      @Autowired DefaultWebSecurityManager defaultWebSecurityManager
  ) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
    // 设置限制的资源
    Map<String, String> map = new HashMap<>();
    // 需要进行验证
    map.put("/**", "authc");
    // 表示资源不需要验证
    map.put("/user/", "anon");
    map.put("/user/login", "anon");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    return shiroFilterFactoryBean;
  }

  @Bean
  public DefaultWebSecurityManager defaultWebSecurityManager(
      @Autowired Realm realm
  ) {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    defaultWebSecurityManager.setRealm(realm);
    return defaultWebSecurityManager;
  }

  @Bean
  public Realm realm(@Autowired HashedCredentialsMatcher credentialsMatcher) {
    CustomerRealm customerRealm = new CustomerRealm();
    customerRealm.setCredentialsMatcher(credentialsMatcher);
    return customerRealm;
  }


  @Bean
  public HashedCredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    hashedCredentialsMatcher.setHashIterations(EncryptionUtils.HASH_ITERATIONS);
    return hashedCredentialsMatcher;
  }



}
