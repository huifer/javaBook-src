package com.huifer.bilibili.monitoring.intf.impl;

import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.SecondModelMonitoring;

public class SecondModelMonitoringImpl implements SecondModelMonitoring {
	@Override
	public void monitorWork(boolean working) {
		System.out.println("work");
	}

	@Override
	public void monitor(SecondModel secondModel) {

	}
}
