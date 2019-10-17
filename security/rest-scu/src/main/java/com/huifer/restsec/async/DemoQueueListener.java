package com.huifer.restsec.async;

import com.huifer.restsec.entity.dto.DemoQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 描述:
 * 监听{@link DeferredResultHolder}
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Slf4j
@Component
public class DemoQueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DemoQueue queue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(
                () -> {


                    while (true) {
                        if (queue.getComplete() != null && queue.getComplete() != "") {

                            String orderId = queue.getComplete();
                            log.info("订单号={}", orderId);
                            DeferredResult<String> result = deferredResultHolder.getMap().get(orderId);
                            log.info("处理结果={}", result);
                            result.setResult(orderId + "  订单处理完毕..........");
                            queue.setComplete(null);

                        } else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
        ).start();

    }
}
