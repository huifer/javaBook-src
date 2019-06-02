package com.huifer.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@RestController
@Slf4j
public class MonoController {

    @GetMapping("/default")
    public String defaultResp() {
        log.info("defaultResp method start");
        String default_hello = doSome("default hello");
        log.info("defaultResp method end");

        return default_hello;
    }


    @GetMapping("/mono")
    public Mono<String> mono() {
        log.info("mono method start");
        Mono<String> mono_hello = Mono.fromSupplier(() -> doSome("mono hello"));
        log.info("mono method end");
        return mono_hello;

    }


    private String doSome(String msg) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return msg;
    }



}
