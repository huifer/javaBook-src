package com.huifer.idgen.my.service.provider;

/**
 * @author: wang
 * @description:
 */
public class PropertyMachineIdProvider implements MachineIdProvider {


    private long machineId;

    @Override
    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}