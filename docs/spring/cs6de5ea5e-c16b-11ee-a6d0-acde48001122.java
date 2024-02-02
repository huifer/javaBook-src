package com.huifer.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@Component(value=  "myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * <p>UserDetails</p>
     * <p>getAuthorities: 获取权限信息</p>
     * <p>getPassword: 获取密码</p>
     * <p>getUsername: 获取用户名</p>
     * <p>isAccountNonExpired: 登录是否过期</p>
     * <p>isAccountNonLocked: 账户是否锁定,是否冻结</p>
     * <p>isCredentialsNonExpired: 密码是否过期</p>
     * <p>isEnabled: 账户是否可用,是否删除用户信息</p>
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名={}", username);
        // todo: 根据用户名查询密码
        //  1. 用户信息获取
        //  2. 过期,冻结等信息也是通过User构造 通过逻辑进行判断

        String pwd = passwordEncoder.encode("123456");
        log.info("加密密码={}", pwd);
        return
                new User(username, pwd,
                        true, true, true, true,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}
