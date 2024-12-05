package com.huifer.design.strategy.active;

/**
 * 促销活动
 */
public class PromotionActive {

  private final PromotionStrategy promotionStrategy;

  public PromotionActive(PromotionStrategy promotionStrategy) {
    this.promotionStrategy = promotionStrategy;
  }

  public static void main(String[] args) {
    PromotionActive promotionActive = new PromotionActive(new CouponStrategy());
    PromotionActive promotionActive1 = new PromotionActive(new EmptyStrategy());
    promotionActive.execute();
    promotionActive1.execute();
  }

  public void execute() {
    this.promotionStrategy.doPromotion();
  }
}
