package com.huifer.idgen.my.service.springboot.config;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.bean.Id;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: wang
 * @description:
 */
public class Run {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-factory-beans.xml");
		GenIdService idServiceFactory =
				(GenIdService) context.getBean("idServiceFactory");
		for (int i = 0; i < 10; i++) {

			long l = idServiceFactory.genId();
			Id id = idServiceFactory.expId(l);
		}
	}

}