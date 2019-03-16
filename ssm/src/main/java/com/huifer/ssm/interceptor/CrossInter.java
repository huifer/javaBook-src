package com.huifer.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-16
 */
public class CrossInter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("Origin") != null) {
            response.setContentType("text/html;charset=UTF-8");
            // 允许那个url
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            // 允许的请求方法
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
            // 允许请求头参数列表
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
            // 带 cookie 访问
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("XDomainRequestAllowed", "1");

            System.out.println("CROS doing");

        }
        return true;
    }

}
