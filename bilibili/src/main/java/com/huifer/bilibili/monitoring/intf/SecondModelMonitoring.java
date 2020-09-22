package com.huifer.bilibili.monitoring.intf;

import com.huifer.bilibili.monitoring.SecondModel;

public interface SecondModelMonitoring extends MonitoringInterface<SecondModel> {

	void monitorWork(boolean working);

}
