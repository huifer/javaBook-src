package com.huifer.bilibili.monitoring.intf.label;

/**
 * 监控,通过字段决定后续工作.
 * 例如传递这个值会后续执行什么事情.
 */
public interface MonitoringFieldWork<T> {

	void fieldWork(T t);
}
