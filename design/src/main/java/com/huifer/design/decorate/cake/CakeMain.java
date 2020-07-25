package com.huifer.design.decorate.cake;

public class CakeMain {

  public static void main(String[] args) {
    BaseCake baseCake;
    // 普通的蛋糕
    baseCake = new OrdinaryCake();
    // 奶油蛋糕
    baseCake = new CreamCakeDecorate(baseCake);
    // 奶油草莓蛋糕
    baseCake = new StrawberryCakeDecorate(baseCake);
    System.out.println(baseCake.msg());
    System.out.println(baseCake.price());
  }

}
