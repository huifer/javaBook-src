package com.huifer.fzjh.exmple;

import com.huifer.fzjh.service.LoadBalanceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoadBalance {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-loadBalance.xml");
		LoadBalanceService loadBalanceFactoryBean = (LoadBalanceService) context.getBean("loadBalanceFactoryBean");
		for (int i = 0; i < 15; i++) {

			String loadBalanceId = loadBalanceFactoryBean.loadBalance();
		}

	}
}
