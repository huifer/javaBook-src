package com.huifer.fzjh.service;

import com.huifer.fzjh.bean.RequestEntity;
import com.huifer.fzjh.bean.ServerWeight;

import java.util.List;

/**
 * 负载均衡基类
 */
public abstract class AbstractLoadBalance implements LoadBalanceService {

    /**
     * 请求
     */
    private RequestEntity requestEntity;
    /**
     * 服务器权重列表
     */
    private List<ServerWeight> serverWeights;


    public AbstractLoadBalance(RequestEntity requestEntity, List<ServerWeight> serverWeights) {
        this.requestEntity = requestEntity;
        this.serverWeights = serverWeights;
    }

    public AbstractLoadBalance() {
    }

}
