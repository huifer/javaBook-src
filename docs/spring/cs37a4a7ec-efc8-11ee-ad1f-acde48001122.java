package com.huifer.design.strategy.active;

/**
 * 没有优惠
 */
public class EmptyStrategy implements PromotionStrategy {

  /**
   * 做优惠
   */
  @Override
  public void doPromotion() {
    System.out.println("没有优惠");
  }
}
