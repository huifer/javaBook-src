package com.huifer.securityuserview.service;

import com.huifer.securityuserview.entity.SysUser;
import com.huifer.securityuserview.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-24
 */
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private SysUserRepository userRepority;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser userEntity = userRepority.findByUsername(s);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return userEntity;
    }

}
