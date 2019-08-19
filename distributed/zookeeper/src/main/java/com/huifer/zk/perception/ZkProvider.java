package com.huifer.zk.perception;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class ZkProvider {

    private ZooKeeper zooKeeper = null;

    private final int timeout = 2000;
    /**
     * 地址列表用","分割 server1:port,server2:port
     */
    private String connects = "localhost:2181";

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    /**
     * 父节点
     */
    private String parentNode = "/server";


    /**
     * 节点递归输出
     *
     * @param path
     * @throws Exception
     */
    public void ls(String path) throws Exception {
        System.out.println(path);
        List<String> list = this.zooKeeper.getChildren(path, null);
        //判断是否有子节点
        if (list.isEmpty() || list == null) {
            return;
        }
        for (String s : list) {
            //判断是否为根目录
            if (path.equals("/")) {
                ls(path + s);
            } else {
                ls(path + "/" + s);
            }
        }
    }

    public ZkProvider() {
        initZk();
    }

    public ZkProvider(ZooKeeper zooKeeper, String connects) {
        this.zooKeeper = zooKeeper;
        this.connects = connects;
        initZk();
    }

    /**
     * 添加节点
     *
     * @param hostname
     * @throws Exception
     */
    public void register(String hostname) {
        String createNode = null;
        try {
            createNode = zooKeeper.create(parentNode, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(hostname + "  is online:" + createNode);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(String hostname) {
        try {
            zooKeeper.delete(hostname, 0);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化zookeeper
     */
    protected void initZk() {
        try {
            zooKeeper = new ZooKeeper(connects, timeout, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
