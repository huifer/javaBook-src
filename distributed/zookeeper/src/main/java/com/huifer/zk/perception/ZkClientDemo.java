package com.huifer.zk.perception;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class ZkClientDemo {

    /**
     * 地址列表用","分割 server1:port,server2:port
     */
    private String servers = "localhost:2181";


    private final int timeout = 2000;

    private ZkClient zkClient = null;
    private String path = "/";

    protected void initZkClient() {
        zkClient = new ZkClient(servers, timeout);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("handleDataChange, dataPath:" + parentPath + ", data:" + currentChilds);
            }
        });
    }

    /**
     * 增加节点
     */
    public void add(String path, Object data) {
        if (zkClient.exists(path)) {
            zkClient.delete(path);
        }
        zkClient.createPersistent(path, data);
    }

    /**
     * 删除节点
     */
    public void remove(String path) {
        zkClient.delete(path);
    }

    /**
     * 更新节点
     */
    public void update(String name, Object data) {
        zkClient.writeData(name, data);
    }


    public ZkClientDemo() {
        initZkClient();
    }

    public ZkClientDemo(String servers ) {
        this.servers = servers;
        initZkClient();
    }
}
