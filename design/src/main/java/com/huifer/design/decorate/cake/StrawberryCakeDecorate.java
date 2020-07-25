package com.huifer.design.decorate.cake;

public class StrawberryCakeDecorate extends CakeDecorate {

  public StrawberryCakeDecorate(BaseCake baseCake) {
    super(baseCake);
  }

  @Override
  protected int price() {
    return this.baseCake.price()+2;
  }

  @Override
  protected String msg() {
    return this.baseCake.msg()+"草莓";
  }
}
