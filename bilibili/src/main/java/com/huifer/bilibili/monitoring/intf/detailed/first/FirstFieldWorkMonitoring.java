package com.huifer.bilibili.monitoring.intf.detailed.first;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldWork;

public interface FirstFieldWorkMonitoring extends MonitoringFieldWork<FirstModel> {
	void workByWorkField(boolean work);

	void workBySecondField(SecondModel secondModel);


	default Class<?> type() {
		return FirstModel.class;
	}
}
