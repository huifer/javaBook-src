package com.huifer.redis.history;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TesServiceTest {
    @Autowired
    WorkService workService;

    @Autowired
    TesService tesService;

    @Test
    public void t() throws IOException {
        tesService.randomAdd(10);
        workService.work();
    }

}