package com.huifer.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@RestController
@Slf4j
public class FluxController {

    @GetMapping("/flux")
    public Flux<String> flux1(@RequestParam String[] lang) {
        return Flux.fromArray(lang);
    }

    @GetMapping("/flux2")
    public Flux<String> flux2(@RequestParam List<String> lang) {

        return Flux.fromStream(lang.stream());
    }

    /**
     * 返回给JS进行操作并不是直接给用户查看
     *
     * @return
     */
    @GetMapping(value = "/see", produces = "text/event-stream")
    public Flux<String> flux3() {
        return Flux.just("java", "python");
    }

}
