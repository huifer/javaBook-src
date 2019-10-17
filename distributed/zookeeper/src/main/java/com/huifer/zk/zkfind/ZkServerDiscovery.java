package com.huifer.zk.zkfind;

/**
 * <p>Title : ZkServerDiscovery </p>
 * <p>Description : zk服务发现</p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public interface ZkServerDiscovery {

    /**
     * 服务发现: 服务名称发现接口地址
     *
     * @param serviceName 服务名称
     * @return 接口地址
     */
    String discover(String serviceName);
}
