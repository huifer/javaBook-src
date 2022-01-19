package dynamic;

import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.FactoryBean;

/**
 * <b>注意：该对象不允许放入到Spring容器中直接管理。如果放入会破坏原有的RabbitMQ的使用！！！</b>
 */
public class DynamicConsumerContainerFactory implements
    FactoryBean<SimpleMessageListenerContainer> {

  /**
   * 交换机类型
   */
  private ExchangeType exchangeType;
  /**
   * 交换机名称
   */
  private String exchangeName;

  /**
   * 队列名称
   */
  private String queue;
  /***
   * rk
   */
  private String routingKey;


  /**
   * 是否自动删除
   */
  private Boolean autoDeleted;
  /**
   * 是否持久
   */
  private Boolean durable;
  /**
   * 是否自动提交
   */
  private Boolean autoAck;

  /**
   * rabbit-mq连接工厂
   */
  private ConnectionFactory connectionFactory;
  /**
   * rabbit 管理端操作类
   */
  private RabbitAdmin rabbitAdmin;
  /**
   * @see org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer#setConcurrentConsumers
   */
  private Integer concurrentNum;
  /**
   * 消息监听器
   */
  private IDynamicConsumer consumer;

  public ExchangeType getExchangeType() {
    return exchangeType;
  }

  public void setExchangeType(ExchangeType exchangeType) {
    this.exchangeType = exchangeType;
  }

  public String getExchangeName() {
    return exchangeName;
  }

  public void setExchangeName(String exchangeName) {
    this.exchangeName = exchangeName;
  }

  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }

  public String getRoutingKey() {
    return routingKey;
  }

  public void setRoutingKey(String routingKey) {
    this.routingKey = routingKey;
  }

  public Boolean getAutoDeleted() {
    return autoDeleted;
  }

  public void setAutoDeleted(Boolean autoDeleted) {
    this.autoDeleted = autoDeleted;
  }

  public Boolean getDurable() {
    return durable;
  }

  public void setDurable(Boolean durable) {
    this.durable = durable;
  }

  public Boolean getAutoAck() {
    return autoAck;
  }

  public void setAutoAck(Boolean autoAck) {
    this.autoAck = autoAck;
  }

  public ConnectionFactory getConnectionFactory() {
    return connectionFactory;
  }

  public void setConnectionFactory(
      ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public RabbitAdmin getRabbitAdmin() {
    return rabbitAdmin;
  }

  public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
    this.rabbitAdmin = rabbitAdmin;
  }

  public Integer getConcurrentNum() {
    return concurrentNum;
  }

  public void setConcurrentNum(Integer concurrentNum) {
    this.concurrentNum = concurrentNum;
  }

  public ChannelAwareMessageListener getConsumer() {
    return consumer;
  }

  public <T extends AbstractMqConsumer> void setConsumer(T consumer) {
    this.consumer = consumer;
  }

  private Exchange buildExchange() {

    switch (exchangeType) {
      case TOPIC:
        return new TopicExchange(exchangeName);
      case DIRECT:
        return new DirectExchange(exchangeName);
      case FANOUT:
        return new FanoutExchange(exchangeName);
      case DEFAULT:
        return new DirectExchange(exchangeName);
      default:
        return new DirectExchange(exchangeName);
    }
  }


  private Queue buildQueue() {
    if (StringUtils.isEmpty(queue)) {
      throw new IllegalArgumentException("queue name should not be null!");
    }

    return new Queue(queue, durable != null && durable, false,
                     autoDeleted == null || autoDeleted);
  }


  private Binding bind(Queue queue, Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
  }


  private void check() {
    if (null == rabbitAdmin || null == connectionFactory) {
      throw new IllegalArgumentException("rabbitAdmin and connectionFactory should not be null!");
    }
  }


  @Override
  public SimpleMessageListenerContainer getObject() throws Exception {
    // 检查数据
    check();

    // 构造队列
    Queue queue = buildQueue();
    // 构造交换机
    Exchange exchange = buildExchange();
    // 设置绑定关系
    Binding binding = bind(queue, exchange);

    // 创建队列，创建queue，创建绑定关系
    rabbitAdmin.declareQueue(queue);
    rabbitAdmin.declareExchange(exchange);
    rabbitAdmin.declareBinding(binding);

    // 船舰消息监听容器
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setRabbitAdmin(rabbitAdmin);
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue);
    container.setPrefetchCount(20);
    container.setConcurrentConsumers(concurrentNum == null ? 1 : concurrentNum);
    container.setAcknowledgeMode(autoAck ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);

    // 设置消息监听器,可以忽略
    if (null != consumer) {
      container.setMessageListener(consumer);
    }
    return container;
  }

  @Override
  public Class<?> getObjectType() {
    return SimpleMessageListenerContainer.class;
  }
}
