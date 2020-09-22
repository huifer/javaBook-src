package com.huifer.bilibili.monitoring.intf.impl;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.FirstModelMonitoring;

public class FirstModelMonitoringImpl implements FirstModelMonitoring {
	@Override
	public void monitor(FirstModel firstModel) {
		this.monitorName(firstModel.getName());
		this.monitorSecondModel(firstModel.getSecondModel());
	}

	@Override
	public void monitorName(String name) {
		System.out.println("name 字段监控");
	}

	@Override
	public void monitorSecondModel(SecondModel secondModel) {

	}
}
