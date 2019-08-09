package com.huifer.fzjh.service;

import com.huifer.fzjh.bean.RequestEntity;
import com.huifer.fzjh.bean.ServerWeight;
import com.huifer.fzjh.exception.LoadBalanceException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 负载均衡算法实现: 加权随机算法
 * 随机数和权重比较,小于权重落入
 */
@Slf4j
public class WeightRandomLoadBalance extends AbstractLoadBalance {
	private int count = -1;
	private RequestEntity requestEntity;

	private List<ServerWeight> serverWeights;

	public WeightRandomLoadBalance(RequestEntity requestEntity, List<ServerWeight> serverWeights) {
		super(requestEntity, serverWeights);
		this.count = serverWeights.size();
		this.requestEntity = requestEntity;
		this.serverWeights = serverWeights;
	}


	@Override
	public String  loadBalance() {
		if (count < 0) {
			throw new LoadBalanceException("机器数量不能小于0");
		}

		HashMap<Integer, Integer> serverHashMap = new HashMap<>();
		for (int i = 0; i < serverWeights.size(); i++) {
			serverHashMap.put(i, serverWeights.get(i).getWeight());
		}
		Integer machineId = getServiceIndex(serverHashMap);
		ServerWeight serverWeight = serverWeights.get(machineId);
		log.info("当前请求信息={},负载均衡计算后的机器ip={},端口={}", requestEntity, serverWeight.getIp(), serverWeight.getPort());
		return serverWeight.getIp() + ":" + serverWeight.getPort();
	}

	static int index = 0;
	static Random random = new Random();

	private static Integer getServiceIndex(HashMap<Integer, Integer> map) {
		int allWeight = map.values().stream().mapToInt(a -> a).sum();
		// 加权随机
		int number = random.nextInt(allWeight);
		for (Map.Entry<Integer, Integer> item : map.entrySet()) {
			if (item.getValue() >= number) {
				return item.getKey();
			}
			number -= item.getValue();
		}
		return -1;
	}


}
