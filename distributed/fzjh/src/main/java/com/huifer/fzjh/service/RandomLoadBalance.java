package com.huifer.fzjh.service;

import com.huifer.fzjh.bean.RequestEntity;
import com.huifer.fzjh.bean.ServerWeight;
import com.huifer.fzjh.exception.LoadBalanceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡算法实现: 按照随机
 * 直接随机一个机器
 */
@Slf4j
public class RandomLoadBalance extends AbstractLoadBalance {
	private int count = -1;
	private RequestEntity requestEntity;
	private List<ServerWeight> serverWeights;

	public RandomLoadBalance(RequestEntity requestEntity, List<ServerWeight> serverWeights) {
		super(requestEntity, serverWeights);
		this.count = serverWeights.size();
		this.requestEntity = requestEntity;
		this.serverWeights = serverWeights;
	}


	@Override
	public String loadBalance() {
		if (count < 0) {
			throw new LoadBalanceException("机器数量不能小于0");
		}

		Random random = new Random();
		int machineId = random.nextInt(count);
		ServerWeight serverWeight = serverWeights.get(machineId);
		log.info("当前请求信息={},负载均衡计算后的机器ip={},端口={}", requestEntity, serverWeight.getIp(), serverWeight.getPort());
		return serverWeight.getIp() + ":" + serverWeight.getPort();
	}
}
