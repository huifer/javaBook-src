package com.huifer.xz.spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XzSpiderTest {
    @Autowired
    private XzSpider xzSpider;

    @Test
    public void cityInfo() {
        xzSpider.cityInfo();

    }
}