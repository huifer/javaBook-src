package dynamic;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;


/**
 * 抽象的消息处理器
 */
public abstract class AbstractMqConsumer implements IDynamicConsumer {

  private static final Logger log = LoggerFactory.getLogger(AbstractMqConsumer.class);
  /**
   * 是否关闭
   */
  private volatile boolean end = false;
  /**
   * 消息监听容器
   */
  private SimpleMessageListenerContainer container;
  /**
   * 是否自动ack
   */
  private boolean autoAck;
  private String label;

  public boolean isEnd() {
    return end;
  }

  public void setEnd(boolean end) {
    this.end = end;
  }

  public SimpleMessageListenerContainer getContainer() {
    return container;
  }

  @Override
  public void setContainer(SimpleMessageListenerContainer container) {
    this.container = container;
    autoAck = container.getAcknowledgeMode().isAutoAck();
  }

  public boolean isAutoAck() {
    return autoAck;
  }

  public void setAutoAck(boolean autoAck) {
    this.autoAck = autoAck;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public void shutdown() {
    container.shutdown();
    end = true;
  }

  /**
   * 提交ack
   */
  protected void autoAck(Message message, Channel channel, boolean success) throws IOException {
    if (autoAck) {
      return;
    }

    if (success) {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } else {
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }
  }

  public void onMessage(Message message, Channel channel) throws Exception {
    try {
      log.info("消息label = [{}] 开始处理, 消息内容 = {} ", label , new String(message.getBody(),"UTF-8"));

      autoAck(message, channel, process(message, channel));
    } catch (Exception e) {
      autoAck(message, channel, false);
      throw e;
    } finally {
      if (end) {
        container.stop();
      }
    }
  }

  /**
   * 处理消息
   */
  public abstract boolean process(Message message, Channel channel)
      throws UnsupportedEncodingException;
}
