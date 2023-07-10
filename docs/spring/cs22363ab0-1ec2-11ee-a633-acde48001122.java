package com.huifer.feign.rest.client;

import com.huifer.feign.annotation.RestClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title : MySayService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@RestClient(name = "zk-Discovery")
public interface MySayService {

    @RequestMapping("/say")
    String say(String message);


}
