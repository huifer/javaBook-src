package com.huifer.idgen.my.service.exmple.springboot;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.bean.enums.Type;
import com.huifer.idgen.my.service.factory.IdServiceFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 1
 * @description:
 */
@Configuration
public class SpringFactoryBeans {

    @Value("${idgen.ips}")
    String ips;

    @Value("${idgen.machine}")
    long machine;

    @Bean
    public GenIdService genIdService() {

        IdServiceFactoryBean idServiceFactoryBean = new IdServiceFactoryBean();
        idServiceFactoryBean.setProviderType(Type.IP);
        idServiceFactoryBean.setMachineId(1L);
        idServiceFactoryBean.init();

        return idServiceFactoryBean.getGenIdService();
    }


}