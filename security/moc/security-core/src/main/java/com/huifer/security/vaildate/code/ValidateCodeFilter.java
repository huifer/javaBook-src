package com.huifer.security.vaildate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
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
public class ValidateCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (
                StringUtils.equals("/auth/form", request.getRequestURI())
                        && StringUtils.equalsIgnoreCase("post", request.getMethod())
        ) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode attribute = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.session_key);
        // imageCode 是登录页上传递的参数值
        String imageCode = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(imageCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (attribute == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (attribute.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.session_key);
            throw new ValidateCodeException("验证码过期");
        }
        if (!StringUtils.equalsIgnoreCase(attribute.getCode(), imageCode)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeController.session_key);
    }
}
