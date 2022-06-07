# 建造者模式
- 将一个复杂的对象的构建与它的表示分离


## 編碼
- 以自行车为例, 车架和轮胎是自行车的两个属性,分别是不同的两个对象

```java

/**
 * 轮子
 */
public class Wheel {

  private Integer size;

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}


/**
 * 车架
 */
public class Frame {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

```

- 定义创建器,并且实现

```java
public abstract class TrekBikeBuilder {

  abstract void buildWheel();

  abstract void buildFrame();

  abstract TrekBike createBike();


}

public class TrekBikeBuilderImpl extends TrekBikeBuilder {

  private final TrekBike trekBike = new TrekBike();

  @Override
  void buildWheel() {
    trekBike.setWheel(new Wheel());
  }

  @Override
  void buildFrame() {
    trekBike.setFrame(new Frame());

  }

  @Override
  TrekBike createBike() {
    return trekBike;
  }

}

```

- 最后需要统筹调度

```java
public class Director {

  private final TrekBikeBuilder trekBikeBuilder;

  public Director(TrekBikeBuilder builder) {
    trekBikeBuilder = builder;
  }

  public TrekBike construct() {
    trekBikeBuilder.buildFrame();
    trekBikeBuilder.buildWheel();

    return trekBikeBuilder.createBike();
  }
}
```