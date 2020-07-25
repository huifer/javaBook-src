package com.huifer.design.decorate.cake;

public class CreamCakeDecorate extends CakeDecorate {

  public CreamCakeDecorate(BaseCake baseCake) {
    super(baseCake);
  }

  @Override
  protected String msg() {
    return this.baseCake.msg() + "+奶油";
  }

  @Override
  protected int price() {
    return this.baseCake.price() + 1;
  }
}
