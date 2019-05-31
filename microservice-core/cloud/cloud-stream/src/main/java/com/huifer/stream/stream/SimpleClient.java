package com.huifer.stream.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>Title : SimpleClient </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-31
 */
public interface SimpleClient {

    @Output("huifer")
    MessageChannel huifer();

}
