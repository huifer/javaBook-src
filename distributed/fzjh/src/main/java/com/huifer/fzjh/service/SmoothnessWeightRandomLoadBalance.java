package com.huifer.fzjh.service;

import com.huifer.fzjh.bean.RequestEntity;
import com.huifer.fzjh.bean.ServerWeight;
import com.huifer.fzjh.exception.LoadBalanceException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负载均衡算法实现: 平滑加权算轮询法
 */
@Slf4j
public class SmoothnessWeightRandomLoadBalance extends AbstractLoadBalance {
    private int count = -1;
    private RequestEntity requestEntity;

    private List<ServerWeight> serverWeights;

    public SmoothnessWeightRandomLoadBalance(RequestEntity requestEntity, List<ServerWeight> serverWeights) {
        super(requestEntity, serverWeights);
        this.count = serverWeights.size();
        this.requestEntity = requestEntity;
        this.serverWeights = serverWeights;
    }

    private static String getServiceIndex(HashMap<Integer, ServerWeight> serverMap) {
        ServerWeight maxWeightServer = null;
        int allWeight = serverMap.values().stream().mapToInt(ServerWeight::getWeight).sum();
        for (Map.Entry<Integer, ServerWeight> item : serverMap.entrySet()) {
            ServerWeight currentServer = item.getValue();
            if (maxWeightServer == null || currentServer.getCurrentWeight() > maxWeightServer.getCurrentWeight()) {
                maxWeightServer = currentServer;
            }
        }
        maxWeightServer.setCurrentWeight(maxWeightServer.getCurrentWeight() - allWeight);
        for (Map.Entry<Integer, ServerWeight> item : serverMap.entrySet()) {
            ServerWeight currentServer = item.getValue();
            currentServer.setCurrentWeight(currentServer.getCurrentWeight() + currentServer.getWeight());
        }
        return maxWeightServer.getIp() + ":" + maxWeightServer.getPort();
    }

    @Override
    public String loadBalance() {
        if (count < 0) {
            throw new LoadBalanceException("机器数量不能小于0");
        }

        HashMap<Integer, ServerWeight> serverHashMap = new HashMap<>();
        for (int i = 0; i < serverWeights.size(); i++) {
            serverHashMap.put(i, serverWeights.get(i));
        }
        String hostPost = getServiceIndex(serverHashMap);
        log.info("当前请求信息={},负载均衡计算后的机器hostPost={}", requestEntity, hostPost);
        return hostPost;
    }


}
