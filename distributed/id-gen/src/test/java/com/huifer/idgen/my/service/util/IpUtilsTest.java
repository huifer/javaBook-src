package com.huifer.idgen.my.service.util;


import org.junit.Test;

public class IpUtilsTest {


    @Test
    public void getHostIp() {
        String hostIp = IpUtils.getHostIp();
        System.out.println(hostIp);
    }

    @Test
    public void getHostName() {
        String hostName = IpUtils.getHostName();
        System.out.println(hostName);
    }
}
