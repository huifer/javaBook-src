package com.huifer.fzjh.factory;

import com.huifer.fzjh.bean.RequestEntity;
import com.huifer.fzjh.bean.ServerWeight;
import com.huifer.fzjh.bean.enums.LoadBalanceEnums;
import com.huifer.fzjh.exception.LoadBalanceException;
import com.huifer.fzjh.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;


public class LoadBalanceFactoryBean implements FactoryBean<LoadBalanceService> {

	@Setter
	private LoadBalanceEnums loadBalanceEnums;

	@Setter
	private List<ServerWeight> serverWeightList;

	public LoadBalanceFactoryBean() {
	}

	/**
	 * 请求
	 */
	@Setter
	private RequestEntity requestEntity;


	@Getter
	private LoadBalanceService loadBalanceService;

	public void init() {
		if (loadBalanceEnums == null) {
			throw new LoadBalanceException("loadBalanceEnums == null");
		}

		switch (loadBalanceEnums) {
			case IP_LOAD_BALANCE:
				this.loadBalanceService = new IpLoadBalance(requestEntity, serverWeightList);
				break;
			case ROUND_ROBIN_LOAD_BALANCE:
				this.loadBalanceService = new RoundRobinLoadBalance(requestEntity, serverWeightList);
				break;
			case RANDOM_LOAD_BALANCE:
				this.loadBalanceService = new RandomLoadBalance(requestEntity, serverWeightList);
				break;
			case WEIGHT_ROUND_ROBIN_LOAD_BALANCE:
				this.loadBalanceService = new WeightRoundRobinLoadBalance(requestEntity, serverWeightList);
				break;
			case SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE:
				this.loadBalanceService = new SmoothnessWeightRandomLoadBalance(requestEntity, serverWeightList);
				break;
			case WEIGHT_RANDOM_LOAD_BALANCE:
				this.loadBalanceService = new WeightRandomLoadBalance(requestEntity, serverWeightList);
				break;
			default:
				this.loadBalanceService = new IpLoadBalance(requestEntity, serverWeightList);
				break;
		}
	}


	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public LoadBalanceService getObject() throws Exception {
		return loadBalanceService;
	}

	@Override
	public Class<?> getObjectType() {
		return LoadBalanceService.class;
	}
}
