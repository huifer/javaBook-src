package com.huifer.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(getClass());

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名={}", username);
        // todo: 根据用户名查询密码
        return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
