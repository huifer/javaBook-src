package com.huifer.zk.regist;

/**
 * <p>Title : MyRegisterCenter </p>
 * <p>Description : 服务注册中心 </p>
 *
 * @author huifer
 * @date 2019-06-12
 */
public interface MyRegisterCenter {

    /**
     * 注册器
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务IP地址
     */
    void register(String serviceName, String serviceAddress) throws Exception;

}
