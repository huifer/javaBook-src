package com.huifer.jenkinsspringboot.service.spider;

import com.alibaba.fastjson.JSONArray;
import com.huifer.jenkinsspringboot.config.RescueTimeApiConfig;
import com.huifer.jenkinsspringboot.entity.rescuetime.DailySummaryFeedRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Date: 2019-09-30
 */
@Slf4j
@Service
public class RescueTimeSpider {
    @Value("${test_api}")
    public String apikey;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RescueTimeApiConfig rescueTimeApiConfig;

    /**
     * 每日摘要信息
     *
     * @return {@link DailySummaryFeedRest}
     */
    public List<DailySummaryFeedRest> dailySummaryData() {

        ResponseEntity<String> forEntity = restTemplate.getForEntity(rescueTimeApiConfig.getDaily_summary_url()
                + "?key=" + apikey, String.class);
        String body = forEntity.getBody();
        List<DailySummaryFeedRest> dailySummaryFeedRests = JSONArray.parseArray(body, DailySummaryFeedRest.class);
        log.info("每日摘要信息={}", dailySummaryFeedRests);
        return dailySummaryFeedRests;
    }

}
