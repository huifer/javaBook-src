package com.huifer.design.builder;

/**
 * 基础对象 trek 自行车
 */
public class TrekBike {

  private Wheel wheel;
  private Frame frame;

  public Wheel getWheel() {
    return wheel;
  }

  public void setWheel(Wheel wheel) {
    this.wheel = wheel;
  }

  public Frame getFrame() {
    return frame;
  }

  public void setFrame(Frame frame) {
    this.frame = frame;
  }
}
