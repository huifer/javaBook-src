package dynamic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface DynamicContainer {

  Map<String, DynamicConsumer> customizeDynamicConsumerContainer = new
      ConcurrentHashMap<>();


  default Map<String, DynamicConsumer> info() {
    return customizeDynamicConsumerContainer;
  }

  default void put(String name, DynamicConsumer consumer) {
    customizeDynamicConsumerContainer.putIfAbsent(name, consumer);
  }

  default String gen(String appCode, String sceneCode) {
    return appCode + "-" + sceneCode;
  }


}
