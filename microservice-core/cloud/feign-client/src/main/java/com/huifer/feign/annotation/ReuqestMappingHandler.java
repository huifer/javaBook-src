package com.huifer.feign.annotation;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

public class ReuqestMappingHandler implements InvocationHandler {

    private final String serviceName;
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private final BeanFactory beanFactory;


    public ReuqestMappingHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestMapping requestMapping = findAnnotation(
                method, RequestMapping.class);
        // 过滤@ResquestMapping
        if (requestMapping != null) {
            String[] uri = requestMapping.value();
            // 方法参数数量
            int parameterCount = method.getParameterCount();
            // 方法参数名称
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            // 方法参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();

            Annotation[] annotations = method.getAnnotations();

            StringBuilder queryStringSB = new StringBuilder();

            StringBuilder URLSB = new StringBuilder();
            URLSB.append("http://").append(serviceName).append("/").append(uri[0]);


            // 获取不到 RequestParam 中的值 下面采用直接写死的方案
//            for (int i = 0; i < parameterCount; i++) {
//
//                RequestParam requestParam = method.getAnnotation(RequestParam.class);
//                if (requestParam != null) {
//
//                    String paramName = parameterNames[i];
//
//                    String requestParamName =
//                            StringUtils.hasText(requestParam.value()) ? requestParam.value()
//                                    : paramName;
//
//                    Class<?> paramType = parameterTypes[i];
//
//                    // uri?param
//                    String requestParamValue =
//                            String.class.equals(paramType) ? (String) args[i]
//                                    : String.valueOf(args[i]);
//                    queryStringSB.append("&").append(requestParamName).append("=")
//                            .append(requestParamValue);
//                }
//            }

            queryStringSB.append("&").append("message=").append(String.valueOf(args[0]));

            String queryString = queryStringSB.toString();
            if (StringUtils.hasText(queryString)) {
                URLSB.append("?").append(queryStringSB);
            }

            String restUrl = URLSB.toString();

            // restTemplate 请求 loadBalancedRest

            RestTemplate r = beanFactory
                    .getBean("restTemplate3", RestTemplate.class);

            Object forObject = r.getForObject(restUrl, method.getReturnType());
            return forObject;
        }
        return null;
    }
}
