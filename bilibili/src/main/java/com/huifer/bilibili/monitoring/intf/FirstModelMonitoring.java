package com.huifer.bilibili.monitoring.intf;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.SecondModel;

public interface FirstModelMonitoring extends MonitoringInterface<FirstModel> {
	@Override
	void monitor(FirstModel firstModel);

	void monitorName(String name);

	void monitorSecondModel(SecondModel secondModel);

}
