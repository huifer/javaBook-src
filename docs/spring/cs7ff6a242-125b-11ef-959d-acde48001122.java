package com.huifer.bilibili.monitoring.intf.detailed.second;

import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldWork;

public interface SecondFieldWorkMonitoring extends MonitoringFieldWork<SecondModel> {
	void workByWorkField(boolean work);

	default Class<?> type() {
		return SecondModel.class;
	}
}
