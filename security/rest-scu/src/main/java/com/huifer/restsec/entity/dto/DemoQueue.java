package com.huifer.restsec.entity.dto;

import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Component
public class DemoQueue {
    /**
     * 下单
     */
    private String place;
    /**
     * 订单完成
     */
    private String complete;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.complete = place;
        }).start();

    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }
}
