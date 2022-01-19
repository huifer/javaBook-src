package dynamic;

import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service("dynamicMqUsingImpl")
public class DynamicMqUsingImpl implements
    DynamicMqUsing {

  public static final String prefix = "mc";
  @Autowired
  private ConnectionFactory connectionFactory;
  @Autowired
  private AmqpTemplate amqpTemplate;


  @Override
  public Map<String, DynamicConsumer> info() {
    return DynamicMqUsing.super.info();
  }


  public <T extends AbstractMqConsumer> void register(String appCode, String sceneCode, T consumer)
      throws Exception {
    if (!has(appCode, sceneCode)) {

      String queueName = gen(appCode, sceneCode);
      DynamicConsumerContainerFactory fac = new DynamicConsumerContainerFactory();
      fac.setExchangeType(ExchangeType.DEFAULT);
      fac.setExchangeName(prefix + appCode);
      fac.setQueue(queueName);
      fac.setRoutingKey(queueName);
      fac.setAutoDeleted(false);
      fac.setDurable(true);
      fac.setAutoAck(false);
      fac.setConnectionFactory(connectionFactory);
      fac.setRabbitAdmin(new RabbitAdmin(connectionFactory));
      fac.setConcurrentNum(10);

      fac.setConsumer(consumer);
      DynamicConsumer dynamicConsumer = new DynamicConsumer(fac);
      put(queueName, dynamicConsumer);
      dynamicConsumer.start();

    }
  }

  private boolean has(String appCode, String sceneCode) {
    return DynamicContainer.customizeDynamicConsumerContainer.containsKey(gen(appCode, sceneCode));
  }




}
