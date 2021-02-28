package com.github.huifer.ctrpluginexample.runner;

import com.github.huifer.ctrpluginexample.ann.CtrPlugin;
import com.github.huifer.ctrpluginexample.utils.InterfaceReflectUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class DemoRunner implements ApplicationRunner, Ordered, ApplicationContextAware {

    /**
     * key: {@link CtrPlugin#uri()} value: {@link CrudRepository}
     */
    public static Map<String, CrudRepoCache> crudRepositoryMap = new ConcurrentHashMap<>(64);

    @Autowired
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, CrudRepository> beansOfType = context.getBeansOfType(CrudRepository.class);
        beansOfType.forEach((k, v) -> {
            CrudRepository v1 = v;
            try {
                //
                Class<?>[] repositoryInterfaces = AopProxyUtils.proxiedUserInterfaces(v1);
                for (Class<?> repositoryInterface : repositoryInterfaces) {
                    List<Class<?>> interfaceGenericLasses = InterfaceReflectUtils
                            .getInterfaceGenericLasses(repositoryInterface,
                                    CrudRepository.class);
                    if (!CollectionUtils.isEmpty(interfaceGenericLasses)) {
                        // entity class
                        Class<?> entityClass = interfaceGenericLasses.get(0);
                        CtrPlugin annotation = entityClass.getAnnotation(CtrPlugin.class);
                        if (annotation != null) {
                            CrudRepoCache crudRepoCache = new CrudRepoCache(
                                    entityClass,
                                    annotation.insertParamClazz(),
                                    annotation.updateParamClazz(),
                                    v1
                            );

                            crudRepositoryMap.put(annotation.uri(), crudRepoCache);
                        }

                        System.out.println();
                    }
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Data
    public static class CrudRepoCache {

        private Class<?> self;
        private Class<?> insertedClass;
        private Class<?> updatedClass;
        private CrudRepository crudRepository;
        public CrudRepoCache(Class<?> self, Class<?> insertedClass, Class<?> updatedClass,
                CrudRepository crudRepository) {
            this.self = self;
            this.insertedClass = insertedClass;
            this.updatedClass = updatedClass;
            this.crudRepository = crudRepository;
        }

    }
}
