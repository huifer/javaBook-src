package com.huifer.bilibili.monitoring.intf.impl;

import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.detailed.second.SecondFieldWorkMonitoring;

public class SecondFieldWorkMonitoringImpl implements SecondFieldWorkMonitoring {
	@Override
	public void workByWorkField(boolean work) {
		System.out.println("第二个model的work");

	}

	@Override
	public void fieldWork(SecondModel secondModel) {
		this.workByWorkField(secondModel.isWorking());
	}
}
