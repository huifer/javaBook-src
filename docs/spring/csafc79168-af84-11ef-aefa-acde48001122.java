package com.huifer.design.builder;

import java.util.Locale.Builder;

/**
 * 对外使用
 */
public class Director {

  private final TrekBikeBuilder trekBikeBuilder;

  public Director(TrekBikeBuilder builder) {
    trekBikeBuilder = builder;
  }

  public TrekBike construct() {
    trekBikeBuilder.buildFrame();
    trekBikeBuilder.buildWheel();

    return trekBikeBuilder.createBike();
  }
}
