package com.huifer.design.observer;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p>Title : Event </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class Event {

    /**
     * 发生事情的单位 (小偷  Subject)
     */
    private Object resource;
    /**
     * 通知目标单位 (警局)
     */
    private Object target;
    /**
     * 回调方法
     */
    private Method callback;
    /**
     * 触发方法
     */
    private String trigger;
    /**
     * 触发时间
     */
    private Date date;


    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"resource\":")
                .append(resource);
        sb.append(",\"target\":")
                .append(target);
        sb.append(",\"callback\":")
                .append(callback);
        sb.append(",\"trigger\":\"")
                .append(trigger).append('\"');
        sb.append(",\"date\":\"")
                .append(date).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

}
