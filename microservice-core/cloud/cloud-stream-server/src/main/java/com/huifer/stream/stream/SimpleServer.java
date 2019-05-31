package com.huifer.stream.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>Title : SimpleServer </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
public interface SimpleServer {

    @Input("huifer")
    SubscribableChannel huifer();


}
