package com.huifer.bilibili.monitoring;

public class FirstModel {
	private String name;

	private SecondModel secondModel;

	private boolean work;

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SecondModel getSecondModel() {
		return secondModel;
	}

	public void setSecondModel(SecondModel secondModel) {
		this.secondModel = secondModel;
	}
}
