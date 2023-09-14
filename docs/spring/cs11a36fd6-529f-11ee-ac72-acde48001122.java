package com.huifer.security.browser;

import com.huifer.security.browser.support.Response;
import com.huifer.security.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
@RestController
public class BrowserSecurityController {
    private Logger log = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping("/auth/require")
    public Response requireAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            log.info("goto = {}", target);
            if (StringUtils.endsWithIgnoreCase(target, "html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());

            }
        }
        return new Response("访问地址需要身份认证");
    }
}
