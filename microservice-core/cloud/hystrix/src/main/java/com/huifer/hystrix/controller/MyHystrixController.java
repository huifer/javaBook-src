package com.huifer.hystrix.controller;

import com.huifer.hystrix.annotation.Fusing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>Title : MyHystrixController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-30
 */
@RestController
public class MyHystrixController {

    private final ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private Random random = new Random();

    @GetMapping("/v1")
    public String v1(@RequestParam String msg) throws Exception {

        Future<String> submit = executorService.submit(
                () -> resultMsg(msg)
        );
        String s;
        try {
            // 强制等待100ms 执行
            s = submit.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            s = errorMsg(msg);
        }
        return s;
    }

    @GetMapping("v2")
    public String v2(@RequestParam String msg) throws Exception {

        Future<String> submit = executorService.submit(
                () -> resultMsg(msg)
        );
        String s = submit.get(100, TimeUnit.MILLISECONDS);
        return s;
    }


    @GetMapping("v3")
    public String v3(@RequestParam String msg)
            throws InterruptedException, ExecutionException, TimeoutException {
        return resultMsg(msg);
    }

    @GetMapping("v4")
    @Fusing(timeout = 200)
    public String v4(@RequestParam String msg) {
        System.out.println("v4 gogogo");
        return resultMsg(msg);
    }


    private String errorMsg(String msg) {
        return "error " + msg;
    }


    private String resultMsg(String msg) {
        try {
            int i = random.nextInt(200);
            System.out.println("sleep " + i + " ms");
            Thread.sleep(i);
            return "msg = " + msg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
