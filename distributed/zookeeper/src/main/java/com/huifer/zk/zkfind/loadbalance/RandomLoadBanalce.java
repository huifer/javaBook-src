package com.huifer.zk.zkfind.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * <p>Title : RandomLoadBanalce </p>
 * <p>Description : 具体随机负载均衡算法</p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public class RandomLoadBanalce extends AbstractLoadBanance {

    @Override
    protected String doRandHost(List<String> servicePaths) {
        int size = servicePaths.size();
        Random random = new Random();
        return servicePaths.get(random.nextInt(size));
    }

}
