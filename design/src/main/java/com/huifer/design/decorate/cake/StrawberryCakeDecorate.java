package com.huifer.design.decorate.cake;

public class StrawberryCakeDecorate extends CakeDecorate {

  public StrawberryCakeDecorate(BaseCake baseCake) {
    super(baseCake);
  }

  @Override
  protected int price() {
    return super.price()+2;
  }

  @Override
  protected String msg() {
    return super.msg()+"草莓";
  }
}
