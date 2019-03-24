package com.huifer.securityuserview.config;

import com.huifer.securityuserview.entity.SysUser;
import com.huifer.securityuserview.repository.SysResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-24
 */

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SysResourceRepository resourceRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        System.out.println(authentication);

        System.out.println(authentication.getPrincipal());
        //获得授权后可得到用户信息
        SysUser user = (SysUser) authentication.getPrincipal();

        //输出登录提示信息
        System.out.println("用户名： " + user.getUsername());

        System.out.println("用户密码： " + user.getPassword());

        System.out.println("角色列表：" + authentication.getAuthorities());

        System.out.println("IP信息 :" + authentication.getDetails());

        System.out.println("是否被授权 :" + authentication.isAuthenticated());

        //登录成功后跳转到默认对应的页面
        String targetUrl = "/home";
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            String roleName = grantedAuthority.getAuthority();
            switch (roleName) {
                case "ADMIN":
                    targetUrl = "/admin";
                    break;
                case "VIP":
                    targetUrl = "/vip";
                    break;
            }
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
