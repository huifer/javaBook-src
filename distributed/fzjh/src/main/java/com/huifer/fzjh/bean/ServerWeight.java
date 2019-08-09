package com.huifer.fzjh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器权重
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerWeight {
	private int weight;
	private int currentWeight;
	private String ip;
	private int port;
}
