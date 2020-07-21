package com.huifer.design.strategy.active;

import java.util.HashMap;
import java.util.Map;

public class PromotionFactory {

  private static final Map<String, PromotionStrategy> map = new HashMap<>();


  static {
    map.put(PromotionKey.COUPON, new CouponStrategy());
  }

  public static PromotionStrategy promotionStrategy(String promotionKey) {
    return map.get(promotionKey) == null ? new EmptyStrategy() : map.get(promotionKey);
  }

  private interface PromotionKey {

    String COUPON = "COUPON";
  }

}
