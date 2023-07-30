package com.huifer.design.observer;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title : EventLisenter </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class EventLisenter {

    protected Map<Enum, Event> eventMap = new HashMap<>();

    public void moveLisenter(Enum eventType, Object target, Method callback) {
        eventMap.put(eventType, new Event(target, callback));
    }

    private void trigger(Event event) {
        event.setResource(this);
        event.setDate(new Date());
        try {

            // 反射调用具体方法
            event.getCallback().invoke(
                    event.getTarget(), event
            );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    protected void trigger(Enum call) {
        if (!eventMap.containsKey(call)) {
            return;
        } else {

            Event event = eventMap.get(call);
            event.setTrigger(call.toString());

            trigger(
                    event
            );
        }
    }


}
