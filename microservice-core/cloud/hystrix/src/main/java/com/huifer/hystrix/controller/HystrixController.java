package com.huifer.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>Title : HystrixController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@RestController
public class HystrixController {

    private Random random = new Random();

    @HystrixCommand(
            fallbackMethod = "errorHystrix",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
            }
    )
    @GetMapping("hystrix")
    public String hystrix() throws Exception {
        int i = random.nextInt(200);
        System.out.println("sleep " + i + "ms");
        Thread.sleep(i);
        return "hello hystrix";
    }

    public String errorHystrix() {
        return "error hystrix";
    }


    @HystrixCommand(
            fallbackMethod = "errorHystrix2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
            }
    )
    @GetMapping("hystrix2")
    public String hystrix2(@RequestParam String msg) throws Exception {
        int i = random.nextInt(200);
        System.out.println("sleep " + i + "ms");
        Thread.sleep(i);
        return "hello hystrix";
    }

    public String errorHystrix2(String msg) {
        return "error hystrix";
    }


}
