package com.huifer.zk.regist;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * <p>Title : MyRegisterCenterImpl </p>
 * <p>Description : 服务注册中心实现 </p>
 *
 * @author huifer
 * @date 2019-06-12
 */
public class MyRegisterCenterImpl implements MyRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();

    }


    @Override
    public void register(String serviceName, String serviceAddress) throws Exception {
        // 注册
        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;

        if (curatorFramework.checkExists().forPath(servicePath) == null) {
            // 判断是否存在下级节点
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(servicePath, "0".getBytes());
        }
        String addressPath = servicePath + "/" + serviceAddress;
        // 临时节点创建
        String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                .forPath(addressPath, "0".getBytes());
        System.out.println("服务注册 " + rsNode);
    }
}
