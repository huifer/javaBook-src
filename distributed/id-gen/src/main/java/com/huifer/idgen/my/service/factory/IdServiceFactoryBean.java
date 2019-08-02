package com.huifer.idgen.my.service.factory;

import com.huifer.idgen.my.service.GenIdService;
import com.huifer.idgen.my.service.GenIdServiceImpl;
import com.huifer.idgen.my.service.IdGenException;
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
			throw new IdGenException("providerType !=null");
		}
		switch (providerType) {
			case IP:
				// 根据ip进行生产
				genIdService = createIpconfigGenIdService(ips);
				break;
			case PROPERTY:
				// 根据机器id进行生产
				genIdService = createMachineIdGenIdService(machineId);
				break;
			default:
				break;
		}
	}

	/**
	 * 机器id生产{@link GenIdService}
	 *
	 * @param machineId 机器id
	 */
	private GenIdService createMachineIdGenIdService(long machineId) {
		PropertyMachineIdProvider propertyMachineIdProvider = new PropertyMachineIdProvider();
		propertyMachineIdProvider.setMachineId(machineId);
		GenIdServiceImpl genIdServiceImpl = new GenIdServiceImpl();
		genIdServiceImpl.setMachineIdProvider(propertyMachineIdProvider);
		if (genMethod != -1) {
			genIdServiceImpl.setGenMethod(genMethod);
		}
		if (type != -1) {
			genIdServiceImpl.setType(type);
		}
		if (version != -1) {
			genIdServiceImpl.setVersion(version);
		}
		genIdServiceImpl.init();
		return genIdServiceImpl;
	}

	/**
	 * 根据ip进行生产{@link GenIdService}
	 *
	 * @param ips ips
	 */
	private GenIdService createIpconfigGenIdService(String ips) {
		IpconfigMachineIpProvider ipconfigMachineIpProvider = new IpconfigMachineIpProvider(ips);

		GenIdServiceImpl genIdServiceImpl = new GenIdServiceImpl();
		genIdServiceImpl.setMachineIdProvider(ipconfigMachineIpProvider);
		if (genMethod != -1) {
			genIdServiceImpl.setGenMethod(genMethod);
		}
		if (type != -1) {
			genIdServiceImpl.setType(type);
		}
		if (version != -1) {
			genIdServiceImpl.setVersion(version);
		}
		genIdServiceImpl.init();
		return genIdServiceImpl;
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