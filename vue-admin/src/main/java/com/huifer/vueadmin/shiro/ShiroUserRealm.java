package com.huifer.vueadmin.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.huifer.vueadmin.entity.db.SysUser;
import com.huifer.vueadmin.service.RoleService;
import com.huifer.vueadmin.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Date: 2019-10-18
 */
@Slf4j
public class ShiroUserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setStoredCredentialsHexEncoded(false);
        matcher.setHashIterations(1024);
        super.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthorizationException();
        }
        SysUser user = (SysUser) this.getAvailablePrincipal(principalCollection);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("用户名不可空");
        }
        QueryWrapper<SysUser> q = new QueryWrapper<>();
        q.eq("username", username);
        SysUser sysUser = sysUserService.getOne(q);

        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }


        Set<String> roles = roleService.getRoleByUserId(sysUser.getId());
        sysUser.getRoles().addAll(roles);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPwd(), getName());
        if (sysUser.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
        }

        return info;
    }
}
