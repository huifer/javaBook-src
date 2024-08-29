package dynamic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class CustomizeDynamicConsumerContainer {

  /**
   * 用于存放全局消费者
   */
  public final Map<String, DynamicConsumer> customizeDynamicConsumerContainer = new
      ConcurrentHashMap<>();

  public Map<String, DynamicConsumer> getCustomizeDynamicConsumerContainer() {
    return customizeDynamicConsumerContainer;
  }
}
