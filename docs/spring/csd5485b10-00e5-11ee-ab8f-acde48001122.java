package org.huifer.rbac.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoServiceTest {

    @Autowired
    DemoService demoService;

    @Test
    void test() {
        demoService.lgs();
    }

    @Test
    void testUpdate() {
        demoService.update();
    }

    @Test
    void testDelete() {
        demoService.delete();
    }
}