package com.huifer.design.strategy.active;

public class Run {

  public static void main(String[] args) {
    PromotionActive active = new PromotionActive(PromotionFactory.promotionStrategy("COUPON"));
    active.execute();
  }

}
