package dynamic;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public interface IDynamicConsumer extends ChannelAwareMessageListener {

  /**
   * 设置消息监听容器
   */
    void setContainer(SimpleMessageListenerContainer container);

  /**
   * 关闭
   */
  default void shutdown() {}
}
