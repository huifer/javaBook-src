package com.huifer.feign.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * <p>Title : FeignClientsRegistart </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
public class FeignClientsRegistart implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private BeanFactory beanfactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        ClassLoader classLoader = importingClassMetadata.getClass().getClassLoader();

        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableRestClient.class.getName());

        // 接口对象类
        Class<?>[] clientClasses = (Class<?>[]) attributes.get("clients");
        // 过滤接口 RestClient
        Arrays.stream(clientClasses).filter(Class::isInterface)
                .filter(interfaceClas ->
                        findAnnotation(interfaceClas, RestClient.class) != null)
                .forEach(
                        restClientClass -> {
                            // 获取注解信息
                            RestClient annotation = findAnnotation(restClientClass,
                                    RestClient.class);
                            String serverName = annotation.name();
                            Object proxyInstance = Proxy
                                    .newProxyInstance(classLoader, new Class[]{restClientClass},
                                            new ReuqestMappingHandler(serverName, beanfactory));

                            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactorBean.class);

                            beanDefinitionBuilder.addConstructorArgValue(proxyInstance);
                            beanDefinitionBuilder.addConstructorArgValue(restClientClass);

                            BeanDefinition beanDefinition = beanDefinitionBuilder
                                    .getBeanDefinition();
                            String beanName = "RestClient" + serverName;
                            registry.registerBeanDefinition(beanName, beanDefinition);
                        });


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanfactory = beanFactory;
    }

    private static class RestClientClassFactorBean implements FactoryBean {

        private final Object proxy;
        private final Class<?> restClientClass;


        public RestClientClassFactorBean(Object proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }


}
