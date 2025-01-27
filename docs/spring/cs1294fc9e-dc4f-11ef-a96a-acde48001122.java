package com.huifer.zk.rpcclient;

import com.huifer.zk.inter.HelloInInterface;
import com.huifer.zk.regist.ZkConfig;
import com.huifer.zk.zkfind.ZkServerDiscovery;
import com.huifer.zk.zkfind.ZkServerDiscoveryImpl;

/**
 * <p>Title : ZkRpcClientRun </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class ZkRpcClientRun2 {

    public static void main(String[] args) {
        ZkServerDiscovery discovery = new ZkServerDiscoveryImpl(ZkConfig.CONNECTION_STR);

        ZkRpcClient zkRpcClient = new ZkRpcClient(discovery);

        for (int i = 0; i < 10; i++) {

            HelloInInterface hello = zkRpcClient.clientProxy(HelloInInterface.class);
            String kjljkll = hello.hello("kjljkll");
            System.out.println(kjljkll);
        }
    }

}
