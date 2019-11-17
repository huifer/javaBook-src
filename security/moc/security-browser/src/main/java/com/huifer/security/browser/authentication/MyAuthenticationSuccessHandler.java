package com.huifer.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huifer.security.properties.LoginType;
import com.huifer.security.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        log.info("登陆成功");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            // json
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // 跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
