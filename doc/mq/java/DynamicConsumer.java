package dynamic;

import com.rabbitmq.client.Channel;
import java.nio.charset.StandardCharsets;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class DynamicConsumer {


  private final SimpleMessageListenerContainer container;
  private final DynamicConsumerContainerFactory factory;

  public DynamicConsumerContainerFactory getFactory() {
    return factory;
  }

  public DynamicConsumer(DynamicConsumerContainerFactory fac) throws Exception {
    this.factory = fac;
    SimpleMessageListenerContainer container = fac.getObject();
    this.container = container;
  }

  public SimpleMessageListenerContainer getContainer() {
    return container;
  }

  /**
   * 开启处理
   */
  public void start() {
    container.start();
  }

  /**
   * 停止处理
   */
  public void stop() {
    container.stop();
  }


  /**
   * 关闭容器
   */
  public void shutdown() {
    container.shutdown();
  }



}
