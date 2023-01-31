package com.huifer.xz.mq;

import com.alibaba.fastjson.JSONObject;
import com.huifer.xz.entity.CityUrlInfo;
import com.huifer.xz.entity.RabbitMqType;
import com.huifer.xz.entity.TWoinfo;
import com.huifer.xz.entity.TXz;
import com.huifer.xz.spider.XzSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@Slf4j
@Component
public class MsgRec {
    @Autowired
    private XzSpider xzSpider;

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.CITY_QUEUE_URL)
    public void workCityUrlQ1(String content) {
        doCityQueueUrl(content);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.CITY_QUEUE_URL)
    public void workCityUrlQ2(String content) {
        doCityQueueUrl(content);
    }


    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.USER_QUEUE_INFO)
    public void workerUser1(String txz) {
        doUserSpider(txz);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.USER_QUEUE_INFO)
    public void workerUser2(String txz) {
        doUserSpider(txz);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.XZ_WO)
    public void workerLineQ1(String twoinfo) {
        // 处理 TWoinfo
        doLineSpider(twoinfo);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.XZ_WO)
    public void workerLineQ2(String twoinfo) {
        // 处理 TWoinfo
        doLineSpider(twoinfo);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.XZ_WO)
    public void workerLineQ3(String twoinfo) {
        // 处理 TWoinfo
        doLineSpider(twoinfo);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqType.XZ_WO)
    public void workerLineQ4(String twoinfo) {
        // 处理 TWoinfo
        doLineSpider(twoinfo);
    }

    /**
     * 处理消息队列{@link RabbitMqType#XZ_WO}
     *
     * @param woinfo
     */
    private void doLineSpider(String woinfo) {
        log.info("处理【路线】消息队列");

        JSONObject object = JSONObject.parseObject(woinfo);
        TWoinfo tWoinfo = object.toJavaObject(TWoinfo.class);
        xzSpider.spiderLine(tWoinfo);
    }

    /**
     * 处理消息队列{@link RabbitMqType#USER_QUEUE_INFO}
     *
     * @param txz
     */
    private void doUserSpider(String txz) {
        log.info("处理【用户信息】消息队列");
        JSONObject object = JSONObject.parseObject(txz);
        TXz xz = object.toJavaObject(TXz.class);
        xzSpider.spiderUserHistory(xz);
    }


    /**
     * 处理消息队列 {@link RabbitMqType#CITY_QUEUE_URL}
     *
     * @param content
     */
    private void doCityQueueUrl(String content) {
        log.info("处理【城市url】消息队列");
        JSONObject object = JSONObject.parseObject(content);
        CityUrlInfo cityUrlInfo = object.toJavaObject(CityUrlInfo.class);
        String res = xzSpider.spiderCityPage(cityUrlInfo);
    }
}
