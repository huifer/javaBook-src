package com.huifer.bilibili.monitoring.intf.monitor;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.impl.FirstFieldChangeMonitoringImpl;
import com.huifer.bilibili.monitoring.intf.impl.SecondFieldWorkMonitoringImpl;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldChange;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldWork;

public class MonitorFactoryImpl implements MonitorFactory {
	@Override
	public MonitoringFieldChange genMonitoringFieldChange(Class<?> type) {

		if (type.equals(FirstModel.class)) {
			return new FirstFieldChangeMonitoringImpl();
		}
		else if (type.equals(SecondModel.class)) {
			return null;
		}
		return null;
	}

	@Override
	public MonitoringFieldWork genMonitoringFieldWork(Class<?> type) {

		if (type.equals(FirstModel.class)) {
			return new FirstFieldChangeMonitoringImpl();
		}
		else if (type.equals(SecondModel.class)) {
			return new SecondFieldWorkMonitoringImpl();
		}
		return null;
	}
}
