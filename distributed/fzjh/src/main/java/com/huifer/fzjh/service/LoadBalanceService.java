package com.huifer.fzjh.service;

import org.springframework.stereotype.Service;

/**
 * 负载均衡算法
 */
@Service
public interface LoadBalanceService {
	/**
	 * 负载均衡
	 */
	String loadBalance();
}
