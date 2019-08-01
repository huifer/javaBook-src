package com.huifer.idgen.my.service.provider;

/**
 * @author: wang
 * @description:
 */
public class PropertyMachineIdsProvider implements MachineIdsProvider {


	private long[] machineIds;


	private int currentIndex;


	public long getMachineId() {
		return machineIds[currentIndex++ % machineIds.length];
	}

	public long[] getMachineIds() {
		return machineIds;
	}

	public void setMachineIds(long[] machineIds) {
		this.machineIds = machineIds;
	}


}