package com.huifer.design.builder;

public class TrekBikeBuilderImpl extends TrekBikeBuilder {

  private final TrekBike trekBike = new TrekBike();

  @Override
  void buildWheel() {
    trekBike.setWheel(new Wheel());
  }

  @Override
  void buildFrame() {
    trekBike.setFrame(new Frame());

  }

  @Override
  TrekBike createBike() {
    return trekBike;
  }

}
