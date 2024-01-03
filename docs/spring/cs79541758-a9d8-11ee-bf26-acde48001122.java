package com.huifer.bilibili.monitoring.intf.impl;

import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldChange;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldWork;
import com.huifer.bilibili.monitoring.intf.monitor.MonitorFactory;
import com.huifer.bilibili.monitoring.intf.monitor.MonitorFactoryImpl;

public class SupperMonitor<T> {

	private final T data;


	private MonitoringFieldChange<T> fieldChange;

	private MonitoringFieldWork<T> fieldWork;


	public SupperMonitor(T o) {
		this.data = o;
		factory(o.getClass());
	}

	public SupperMonitor(T data, MonitoringFieldChange<T> fieldChange, MonitoringFieldWork<T> fieldWork) {
		this.data = data;
		this.fieldChange = fieldChange;
		this.fieldWork = fieldWork;
	}

	private void factory(Class<?> type) {
		MonitorFactory factory = new MonitorFactoryImpl();
		this.fieldChange = factory.genMonitoringFieldChange(type);
		this.fieldWork = factory.genMonitoringFieldWork(type);
	}

	public void monitor() {
		if (fieldChange != null) {

			this.fieldChange.filedChange(data);
		}
		if (fieldWork != null) {

			this.fieldWork.fieldWork(data);
		}
	}

}
