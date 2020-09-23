package com.huifer.bilibili.monitoring.intf.impl;

import com.huifer.bilibili.monitoring.FirstModel;
import com.huifer.bilibili.monitoring.SecondModel;
import com.huifer.bilibili.monitoring.intf.detailed.first.FirstFieldChangeMonitoring;
import com.huifer.bilibili.monitoring.intf.detailed.first.FirstFieldWorkMonitoring;
import com.huifer.bilibili.monitoring.intf.label.MonitoringInterface;

public class FirstFieldChangeMonitoringImpl implements MonitoringInterface<FirstModel>, FirstFieldWorkMonitoring, FirstFieldChangeMonitoring {
	public static void main(String[] args) {
		FirstFieldChangeMonitoringImpl firstFieldChangeMonitoring = new FirstFieldChangeMonitoringImpl();
		SecondModel secondModel = new SecondModel();
		secondModel.setWorking(false);

		FirstModel firstModel = new FirstModel();
		firstModel.setName("aaaaaa");
		firstModel.setWork(true);
		firstModel.setSecondModel(secondModel);

		firstFieldChangeMonitoring.monitor(firstModel);
	}

	@Override
	public void nameChangeMonitor(String oldName, String newName) {
		System.out.println("数据变更" + oldName + "\t" + newName);
	}

	@Override
	public void filedChange(FirstModel firstModel) {
		// 字段变动任务
		this.nameChangeMonitor("", firstModel.getName());
	}

	@Override
	public void workByWorkField(boolean work) {
		if (work) {
			System.out.println("开始工作");
		}
	}

	@Override
	public void workBySecondField(SecondModel secondModel) {
		SupperMonitor<SecondModel> secondModelSupperMonitor = new SupperMonitor<>(secondModel);
		secondModelSupperMonitor.monitor();
	}

	@Override
	public Class<?> type() {
		return FirstModel.class;
	}

	@Override
	public void fieldWork(FirstModel firstModel) {
		// 字段行为任务
		this.workByWorkField(firstModel.isWork());
		this.workBySecondField(firstModel.getSecondModel());
	}

	@Override
	public void monitor(FirstModel firstModel) {
		SupperMonitor<FirstModel> firstModelSupperMonitor = new SupperMonitor<>(firstModel, this, this);

		firstModelSupperMonitor.monitor();
	}
}
