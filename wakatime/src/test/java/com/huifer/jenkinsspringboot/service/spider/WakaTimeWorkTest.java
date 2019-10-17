package com.huifer.jenkinsspringboot.service.spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WakaTimeWorkTest {
    @Autowired
    private WakaTimeWork wakaTimeWork;

    @Test
    public void updateWakaTimeUserInfo() {
        wakaTimeWork.updateWakaTimeUserInfo();
    }

    @Test
    public void updateWakaHeart() {
        wakaTimeWork.updateWakaHeart();
    }

    @Test
    public void updateWakaProject() {
        wakaTimeWork.updateWakaProject();
    }

    @Test
    public void updateDurations() {
        wakaTimeWork.updateDurations();
    }
}