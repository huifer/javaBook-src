package com.huifer.heartbeat.socket;

import java.io.Serializable;

/**
 * 消息包
 */
public class LiveBack implements Serializable {

    private static final long serialVersionUID = 6512279542859907453L;


    private String msg;

    public LiveBack() {
    }

    public LiveBack(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LiveBack{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
