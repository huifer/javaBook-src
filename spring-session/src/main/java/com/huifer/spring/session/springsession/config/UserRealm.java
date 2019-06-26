package com.huifer.spring.session.springsession.config;

import com.huifer.spring.session.springsession.entity.SysUser;
import com.huifer.spring.session.springsession.entity.enums.ForbiddenEnum;
import com.huifer.spring.session.springsession.service.SysResourceService;
import com.huifer.spring.session.springsession.service.SysUserService;
import com.huifer.spring.session.springsession.util.ShrioUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title : UserRealm </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo
                .setStringPermissions(sysResourceService.selectUserPerms(ShrioUtil.getSysUserId()));
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserService.findByAccount(token.getUsername());

        if (sysUser == null) {
            log.error("没有该账号");
        }
        if (ForbiddenEnum.DISABLE.getCode().toString().equals(sysUser.getForbidden())) {
            log.error("没有该账号");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser,
                sysUser.getPassword(), getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
        return simpleAuthenticationInfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5HashedCredentialsMatcher = new HashedCredentialsMatcher();
        md5HashedCredentialsMatcher.setHashAlgorithmName(ShrioUtil.TYPE);
        md5HashedCredentialsMatcher.setHashIterations(ShrioUtil.count);
        md5HashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(md5HashedCredentialsMatcher);
    }
}
