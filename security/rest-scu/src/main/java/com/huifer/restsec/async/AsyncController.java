package com.huifer.restsec.async;

import com.huifer.restsec.entity.dto.DemoQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Slf4j
@RestController
public class AsyncController {


    @Autowired
    private DemoQueue demoQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public DeferredResult<String> order() {
        log.info("主线程");
        // 1. 生产id
        String orderId = UUID.randomUUID().toString();
        // 2. 设置id
        demoQueue.setPlace(orderId);
        // 3. 放入消息队列
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderId, result);
        // 4. 返回
        return result;
    }

    /**
     * *  java.util.concurrent.Callable 请求形式
     * *      劣势, 主线程 唤醒附加线程
     *
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/{id}")
    public Callable<String> id(
            @PathVariable("id") String id
    ) throws InterruptedException {

        log.info("主线程, id = {}", id);

        Callable<String> stringCallable = () -> {
            Thread.sleep(3000);
            log.info("call 线程  , id = {}", id);
            return id;
        };

        return stringCallable;
    }
}
