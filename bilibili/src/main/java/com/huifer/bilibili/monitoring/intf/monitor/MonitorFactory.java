package com.huifer.bilibili.monitoring.intf.monitor;

import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldChange;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldWork;

public interface MonitorFactory {
	MonitoringFieldChange genMonitoringFieldChange(Class<?> type);

	MonitoringFieldWork genMonitoringFieldWork(Class<?> type);

}
