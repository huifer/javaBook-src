package com.huifer.zk.perception;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

public class ZkProviderTest {

    private ZkProvider zk = null;

    @Test
    public void initZkPro() throws KeeperException, Exception {
        zk = new ZkProvider();
        zk.ls("/");
//        zk.getZooKeeper().create("/server", "server".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }


}