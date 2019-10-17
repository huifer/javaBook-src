package com.huifer.zk.zkfind.loadbalance;

import java.util.List;

/**
 * <p>Title : LoadBanalce </p>
 * <p>Description : 负载均衡接口</p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public interface LoadBanalce {

    /**
     * 随机一个服务地址
     *
     * @param servicePaths 服务地址
     * @return
     */
    String randHost(List<String> servicePaths);

}
