package com.huifer.xz.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("city_queue_url")
    private Queue cityQueueUrl;
    @Autowired
    @Qualifier("user_queue_info")
    private Queue userQueueInfo;

    @Autowired
    @Qualifier("xz_wo")
    private Queue xzwo;

    /**
     * 发送到 city_queue_url队列中 数据内容{@link com.huifer.xz.entity.CityUrlInfo}
     *
     * @param cityInfo
     */
    public void sendCityUrl(String cityInfo) {
        rabbitTemplate.convertAndSend(cityQueueUrl.getName(), cityInfo);
    }

    /**
     * 发送到 user_queue_info 数据内容{@link com.huifer.xz.entity.TXz}
     *
     * @param userInfo
     */
    public void sendUserInfo(String userInfo) {
        rabbitTemplate.convertAndSend(userQueueInfo.getName(), userInfo);
    }

    public void sendWo(String wo) {
        rabbitTemplate.convertAndSend(xzwo.getName(), wo);
    }

}