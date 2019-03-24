package com.huifer.security.service;

import com.huifer.security.entity.RoleEntity;
import com.huifer.security.entity.UserEntity;
import com.huifer.security.repository.UserRepority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
@Component
public class UserServiceForSecurity implements UserDetailsService {
    @Autowired
    private UserRepority userRepority;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Specification<UserEntity> fu = (Specification<UserEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("username"), s
        );
        UserEntity userEntity = userRepority.findOne(fu).get();
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        RoleEntity roleEntity = userEntity.gettRoleById();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
