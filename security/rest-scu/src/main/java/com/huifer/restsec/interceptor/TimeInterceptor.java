//package com.huifer.restsec.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 描述:
// * 拦截器没有办法获取参数值
// *
// * @author: huifer
// * @date: 2019-10-07
// */
//@Slf4j
//@Component
//public class TimeInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("preHandle");
//        request.setAttribute("start_time", System.currentTimeMillis());
//        HandlerMethod bean = ((HandlerMethod) handler);
//        String name = bean.getBean().getClass().getSimpleName();
//        String methodName = bean.getMethod().getName();
//        log.info("执行类={},执行方法={}", name, methodName);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle");
//        long start = Long.parseLong(String.valueOf(request.getAttribute("start_time")));
//        long l = System.currentTimeMillis() - start;
//        log.info("耗时={}", l);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("afterCompletion");
//        long start = Long.parseLong(String.valueOf(request.getAttribute("start_time")));
//        long l = System.currentTimeMillis() - start;
//        log.info("耗时={}", l);
//        log.info("异常={}", ex);
//    }
//}
