package com.huifer.fzjh.bean.enums;

public enum LoadBalanceEnums {
	/**
	 * 1: ip hash
	 */
	IP_LOAD_BALANCE("IP_LOAD_BALANCE"),
	/**
	 * 2: 轮询
	 */
	ROUND_ROBIN_LOAD_BALANCE("ROUND_ROBIN_LOAD_BALANCE"),
	/**
	 * 3: 随机
	 */
	RANDOM_LOAD_BALANCE("RANDOM_LOAD_BALANCE"),
	/**
	 * 4: 加权轮询
	 */
	WEIGHT_ROUND_ROBIN_LOAD_BALANCE("WEIGHT_ROUND_ROBIN_LOAD_BALANCE"),
	/**
	 * 5: 平滑加权轮询
	 */
	SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE("SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE"),
	/**
	 * 6: 加权随机
	 */
	WEIGHT_RANDOM_LOAD_BALANCE("WEIGHT_RANDOM_LOAD_BALANCE"),


	;
	private String type;

	LoadBalanceEnums(String type) {
		this.type = type;
	}

	public static LoadBalanceEnums parse(String type) {
		if (type.equalsIgnoreCase("IP_LOAD_BALANCE")) {
			return IP_LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("ROUND_ROBIN_LOAD_BALANCE")) {
			return ROUND_ROBIN_LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("RANDOM_LOAD_BALANCE")) {
			return RANDOM_LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("WEIGHT_ROUND_ROBIN_LOAD_BALANCE")) {
			return WEIGHT_ROUND_ROBIN_LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE")) {
			return SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE;
		} else if (type.equalsIgnoreCase("WEIGHT_RANDOM_LOAD_BALANCE")) {
			return WEIGHT_RANDOM_LOAD_BALANCE;
		}
		return null;
	}

	public static LoadBalanceEnums parse(long type) {
		if (type == 1) {
			return IP_LOAD_BALANCE;
		} else if (type == 2) {
			return ROUND_ROBIN_LOAD_BALANCE;
		} else if (type == 3) {
			return RANDOM_LOAD_BALANCE;
		} else if (type == 4) {
			return WEIGHT_ROUND_ROBIN_LOAD_BALANCE;
		} else if (type == 5) {
			return SMOOTHNESS_WEIGHT_RANDOM_LOAD_BALANCE;
		} else if (type == 6) {
			return WEIGHT_RANDOM_LOAD_BALANCE;
		}
		return null;
	}


}
