package com.huifer.design.decorate.cake;

public class CreamCakeDecorate extends CakeDecorate {

  public CreamCakeDecorate(BaseCake baseCake) {
    super(baseCake);
  }

  @Override
  protected String msg() {
    return super.msg() + "+奶油";
  }

  @Override
  protected int price() {
    return super.price() + 1;
  }
}
