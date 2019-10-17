package com.huifer.jenkinsspringboot.service.spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WakaTestDataDownloadTest {

    @Autowired
    private WakaTestDataDownload wakaTestDataDownload;

    @Test
    public void inst() {
        wakaTestDataDownload.inst();
    }

    @Test
    public void insert() {
        wakaTestDataDownload.insert();
    }

    @Test
    public void history() {
        wakaTestDataDownload.history();
    }
}
