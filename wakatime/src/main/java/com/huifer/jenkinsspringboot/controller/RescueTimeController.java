package com.huifer.jenkinsspringboot.controller;

import com.huifer.jenkinsspringboot.entity.rescuetime.DailySummaryFeedRest;
import com.huifer.jenkinsspringboot.service.spider.RescueTimeSpider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date: 2019-09-30
 */
@Api(value = "rescue_time Controller")
@RestController
@RequestMapping("/rescuetime")
public class RescueTimeController {
    @Autowired
    private RescueTimeSpider rescueTimeSpider;

    @ApiOperation(value = "每日摘要信息")
    @GetMapping("/daily_summary_url")
    public List<DailySummaryFeedRest> dailySummaryData() {
        return rescueTimeSpider.dailySummaryData();
    }
}
