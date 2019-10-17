package com.huifer.springmybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring_*.xml")
public class AccountServiceImplTest {
    @Resource
    private AccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("张三", "李四", 100);
    }


}