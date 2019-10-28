package com.huifer.xz.config;

import com.huifer.xz.entity.RabbitMqType;
import com.huifer.xz.entity.TXz;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@EnableRabbit
@Configuration
public class RabbitMqConfig {


    //RabbitMQ的配置信息
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitMqType.CITY_EXCHANGE);
    }

    /**
     * 存放 {@link com.huifer.xz.entity.CityUrlInfo} 队列
     *
     * @return
     */
    @Bean(value = "city_queue_url")
    public Queue cityQueueUrl() {
        return new Queue(RabbitMqType.CITY_QUEUE_URL, true);
    }


    /**
     * 存放 {@link TXz} 队列
     *
     * @return
     */
    @Bean(value = "user_queue_info")
    public Queue userQueueInfo() {
        return new Queue(RabbitMqType.USER_QUEUE_INFO, true);
    }

    @Bean(value = "xz_wo")
    public Queue xzWo() {
        return new Queue(RabbitMqType.XZ_WO, true);
    }


//    @Bean
//    public Queue cityQueue() {
//        return new Queue(RabbitMqType.CITY_QUEUE, true);
//    }
//
//    @Bean
//    public Queue cityQueueERROR() {
//        return new Queue(RabbitMqType.CITY_QUEUE_ERROR, true);
//    }
//
//    @Bean
//    public Queue cityQueueOK() {
//        return new Queue(RabbitMqType.CITY_QUEUE_OK, true);
//    }
//
//    @Bean
//    public Queue cityUrl() {
//        return new Queue(RabbitMqType.CITY_QUEUE_URL, true);
//    }
//    @Bean
//    public Binding bindingCityUrl() {
//        return BindingBuilder.bind(cityUrl()).to(defaultExchange()).with(RabbitMqType.CITY_ROUTING_KEY);
//    }
//    @Bean
//    public Binding bindingCityId() {
//        return BindingBuilder.bind(cityQueue()).to(defaultExchange()).with(RabbitMqType.CITY_ROUTING_KEY);
//    }
//
//    @Bean
//    public Binding bindingCityIdError() {
//        return BindingBuilder.bind(cityQueueERROR()).to(defaultExchange()).with(RabbitMqType.CITY_ROUTING_KEY);
//    }
//
//
//    @Bean
//    public Binding bindingCityIdOK() {
//        return BindingBuilder.bind(cityQueueOK()).to(defaultExchange()).with(RabbitMqType.CITY_ROUTING_KEY);
//    }


    //建立一个连接容器，类型数据库的连接池
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(true);// 确认机制
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());
        factory.setPrefetchCount(0);
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
