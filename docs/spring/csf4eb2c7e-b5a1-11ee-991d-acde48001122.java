package com.huifer.design.strategy.active;

public class CouponStrategy implements PromotionStrategy {

  /**
   * 做优惠
   */
  @Override
  public void doPromotion() {
    System.out.println("优惠卷");
  }
}
