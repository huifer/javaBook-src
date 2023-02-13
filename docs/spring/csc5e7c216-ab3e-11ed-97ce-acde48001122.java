package com.huifer.design.builder;

public abstract class TrekBikeBuilder {

  abstract void buildWheel();

  abstract void buildFrame();

  abstract TrekBike createBike();


}
