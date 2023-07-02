package com.huifer.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        CuratorFramework build = CuratorFrameworkFactory.builder().connectString("127.0.0.1:32775")
                .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        build.start();
        build.create().creatingParentsIfNeeded().forPath("/node/demo1");
    }
}
