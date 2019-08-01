package com.huifer.idgen.my.service.factory;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.GenIdServiceImpl;
import com.huifer.idgen.my.service.bean.enums.Type;
import com.huifer.idgen.my.service.provider.IpconfigMachineIpProvider;
import com.huifer.idgen.my.service.provider.PropertyMachineIdProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author: wang
 * @description:
 */
public class IdServiceFactoryBean implements FactoryBean<GenIdService> {


	@Setter
	private Type providerType;
	@Setter
	private long machineId;


	@Setter
	private String ips;
	@Setter
	private long genMethod = -1;
	private long type = -1;
	private long version = -1;
	@Getter
	private GenIdService genIdService;

	public void init() {
		if (providerType == null) {
			throw new RuntimeException("providerType !=null");
		}
		switch (providerType) {
			case IP:
				genIdService = createIpconfigGenIdService(ips);
				break;
			case PROPERTY:
				genIdService = createMachineIdGenIdService(machineId);
				break;
			default:
				break;
		}
	}

	private GenIdService createMachineIdGenIdService(long machineId) {
		PropertyMachineIdProvider propertyMachineIdProvider = new PropertyMachineIdProvider();
		propertyMachineIdProvider.setMachineId(machineId);
		GenIdServiceImpl genIdService = new GenIdServiceImpl();
		genIdService.setMachineIdProvider(propertyMachineIdProvider);
		if (genMethod != -1) {
			genIdService.setGenMethod(genMethod);
		}
		if (type != -1) {
			genIdService.setType(type);
		}
		if (version != -1) {
			genIdService.setVersion(version);
		}
		genIdService.init();
		return genIdService;
	}


	private GenIdService createIpconfigGenIdService(String ips) {
		IpconfigMachineIpProvider ipconfigMachineIpProvider = new IpconfigMachineIpProvider(ips);

		GenIdServiceImpl genIdService = new GenIdServiceImpl();
		genIdService.setMachineIdProvider(ipconfigMachineIpProvider);
		if (genMethod != -1) {
			genIdService.setGenMethod(genMethod);
		}
		if (type != -1) {
			genIdService.setType(type);
		}
		if (version != -1) {
			genIdService.setVersion(version);
		}
		genIdService.init();
		return genIdService;
	}


	@Override
	public GenIdService getObject() throws Exception {
		return genIdService;
	}

	@Override
	public Class<?> getObjectType() {
		return GenIdService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}