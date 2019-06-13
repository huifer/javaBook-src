package com.huifer.zk.zkfind;

import com.huifer.zk.regist.ZkConfig;
import com.huifer.zk.zkfind.loadbalance.LoadBanalce;
import com.huifer.zk.zkfind.loadbalance.RandomLoadBanalce;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>Title : ZkServerDiscoveryImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkServerDiscoveryImpl implements ZkServerDiscovery {

    private CuratorFramework curatorFramework;
    private List<String> servicePaths;

    private String ads;

    public ZkServerDiscoveryImpl(String address) {
        this.ads = address;

        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ads)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();

    }

    @Override
    public String discover(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            servicePaths = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerWatcher(path);
        // 随机负载均衡
        LoadBanalce loadBanalce = new RandomLoadBanalce();
        return loadBanalce.randHost(servicePaths);
    }

    /**
     * 监听服务节点的变化情况
     */
    private void registerWatcher(String path) {
        try {

            PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);
            PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework, pathChildrenCacheEvent) ->
                    servicePaths = curatorFramework.getChildren().forPath(path);

            childrenCache.getListenable().addListener(pathChildrenCacheListener);
            childrenCache.start();
        } catch (Exception e) {
            System.out.println("注册 watcher 失败");
            e.printStackTrace();
        }

    }
}
