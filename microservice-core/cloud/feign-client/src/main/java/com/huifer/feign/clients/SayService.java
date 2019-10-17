package com.huifer.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Title : SayService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@FeignClient(name = "${find.server.name}")
public interface SayService {

    /**
     * 获取  com.huifer.zk.controller.SayController#say(java.lang.String) 返回信息
     */
    @GetMapping("/say")
    String say(@RequestParam("message") String message);


}
