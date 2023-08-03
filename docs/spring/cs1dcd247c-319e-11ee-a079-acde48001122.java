package com.huifer.bilibili.monitoring.intf.detailed.first;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.intf.label.MonitoringFieldChange;

public interface FirstFieldChangeMonitoring extends MonitoringFieldChange<FirstModel> {

	void nameChangeMonitor(String oldName, String newName);


	default Class<?> type() {
		return FirstModel.class;
	}
}
