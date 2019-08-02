package com.huifer.idgen.my.service.exmple;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.bean.enums.Type;
import com.huifer.idgen.my.service.factory.IdServiceFactoryBean;

public class JavaIdGenDemo {


	public static void main(String[] args) throws InterruptedException {
		IdServiceFactoryBean idServiceFactoryBean = new IdServiceFactoryBean();
		idServiceFactoryBean.setProviderType(Type.PROPERTY);
		idServiceFactoryBean.setMachineId(1L);
		idServiceFactoryBean.init();
		GenIdService genIdService = idServiceFactoryBean.getGenIdService();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000L);
		}
	}
}
