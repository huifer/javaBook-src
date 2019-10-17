package com.huifer.restsec.fillter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * 描述:
 * filter 不知道是那个控制器 ， 那个方法 执行
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init filter");
    }

    @Override
    public void destroy() {
        log.info("destroy filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter start");
        long nowTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();
        long workTime = endTime - nowTime;
        log.info("操作时间={}", workTime);
        log.info("filter end");
    }
}
