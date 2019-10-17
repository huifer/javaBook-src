package com.huifer.idgen.my.service.provider;

import com.huifer.idgen.my.service.IdGenException;
import com.huifer.idgen.my.service.util.IpUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author: wang
 * @description:
 */
public class IpconfigMachineIpProvider implements MachineIdProvider {


    private long machineId;

    private HashMap<String, Long> ipsTables = new HashMap<>();

    public IpconfigMachineIpProvider(String ips) {
        ipsSet(ips);
        init();
    }

    private void ipsSet(String ips) {

        if (!StringUtils.isEmpty(ips)) {
            String[] split = ips.split(",");
            for (int i = 0; i < split.length; i++) {
                ipsTables.put(split[i], Long.valueOf(i));
            }
        }
    }

    public void init() {
        String hostIp = IpUtils.getHostIp();
        if (StringUtils.isEmpty(ipsTables)) {
            throw new IdGenException("ips 空");
        }
        if (!ipsTables.containsKey(hostIp)) {
            throw new IdGenException("ip表空");
        }

        machineId = ipsTables.get(hostIp);

    }

    @Override
    public long getMachineId() {
        return machineId;
    }
}