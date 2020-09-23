package com.huifer.bilibili.monitoring.intf.label;

/**
 * 监控:字段变更.
 * 字段变更后需要做的事情
 */
public interface MonitoringFieldChange<T> {

	void filedChange(T t);

}
