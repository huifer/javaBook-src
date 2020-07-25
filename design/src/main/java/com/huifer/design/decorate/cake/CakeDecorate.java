package com.huifer.design.decorate.cake;

public abstract class CakeDecorate extends BaseCake {

  protected final BaseCake baseCake;

  public CakeDecorate(BaseCake baseCake) {
    this.baseCake = baseCake;
  }

  @Override
  protected int price() {
    return baseCake.price();
  }

  @Override
  protected String msg() {
    return baseCake.msg();
  }

}
