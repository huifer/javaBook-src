package com.huifer.redis.top;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TopInterfaceImplTest {

    @Autowired
    private TopInterface topInterface;

    public static final String kk = "RK";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Before
    public void init() {
        stringRedisTemplate.delete(kk);
    }

    @Test
    public void add() {
        topInterface.addScore(kk, "A", 0);
        topInterface.addScore(kk, "A", 1);
        topInterface.addScore(kk, "A", 1);
        topInterface.addScore(kk, "A", 1);


        topInterface.addScore(kk, "B", 2);
        topInterface.addScore(kk, "C", 4);
        topInterface.addScore(kk, "D", 4);
        topInterface.addScore(kk, "E", 4);
        topInterface.addScore(kk, "F", 4);
        topInterface.addScore(kk, "G", 4);
        topInterface.addScore(kk, "H", 4);


        Set<String> top = topInterface.getTop(kk, 2);
        System.out.println(top);


        Set<String> limit = topInterface.getLimit(kk, 3, 6);
        System.out.println(limit);
    }

}