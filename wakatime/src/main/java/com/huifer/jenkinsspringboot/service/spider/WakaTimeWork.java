package com.huifer.jenkinsspringboot.service.spider;

import com.huifer.jenkinsspringboot.entity.db.UserApiPO;
import com.huifer.jenkinsspringboot.entity.db.WakaUserinfoPO;
import com.huifer.jenkinsspringboot.mapper.UserApiPOMapper;
import com.huifer.jenkinsspringboot.mapper.WakaUserinfoMapper;
import com.huifer.jenkinsspringboot.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * waka time 定时任务
 *
 * @author huifer
 * @date 2019-09-29
 */
@Slf4j
@Service
public class WakaTimeWork {

    @Autowired
    private UserApiPOMapper userApiPOMapper;
    @Autowired
    private WakaSpider wakaSpider;
    @Autowired
    private WakaUserinfoMapper wakaUserinfoMapper;


    @Transactional(rollbackFor = Exception.class)
    public void historySeven() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userApiPO : userApiList) {
            wakaSpider.getAndSetHistorySeven(userApiPO.getApiKey());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void summary() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userApiPO : userApiList) {
            wakaSpider.getAndSetSummary(userApiPO.getApiKey(), DateUtils.getYestday());
        }

    }


    /**
     * 更新用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWakaTimeUserInfo() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userApiPO : userApiList) {
            WakaUserinfoPO wakaUserinfoPO = wakaSpider.userInfo(userApiPO.getApiKey());
            wakaUserinfoPO.setUpdateTime(new Date());
            WakaUserinfoPO dbInfo = wakaUserinfoMapper.selectByPrimaryKey(wakaUserinfoPO.getApiKey());
            if (dbInfo != null) {
                wakaUserinfoMapper.updateByPrimaryKey(wakaUserinfoPO);
            } else {
                wakaUserinfoMapper.insert(wakaUserinfoPO);
            }
        }
    }


    /**
     * 下载heart数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWakaHeart() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userInfo : userApiList) {
            wakaSpider.getAndSetHeart(DateUtils.getYestday(), userInfo.getApiKey(), userInfo.getId());
        }
    }

    /***
     * 下载project 信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWakaProject() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userApiPO : userApiList) {
            wakaSpider.getAndSetProjects(userApiPO.getApiKey());
        }
    }

    /**
     * 下载duration 信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateDurations() {
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (UserApiPO userApiPO : userApiList) {
            wakaSpider.getAndSetDurations(DateUtils.getYestday(), userApiPO.getApiKey(), userApiPO.getId());
        }
    }

    /**
     * 七日历史记录
     */
    @Scheduled(cron = "0 5  0 1/7 * ?")
    public void history() {
        log.info("开始执行定时任务:下载history Seven 数据");
        this.historySeven();
    }

    /**
     * 每天00:05:00 启动下载project数据
     */
    @Scheduled(cron = "0 5 0 * * ? ")
    public void wakaSummary() {
        log.info("开始执行定时任务:下载 summary 数据");
        this.summary();
    }


    /**
     * 每天00:05:00 启动下载project数据
     */
    @Scheduled(cron = "0 5 0 * * ? ")
    public void wakaDurations() {
        log.info("开始执行定时任务:下载durations数据");
        this.updateDurations();
    }


    /**
     * 每天00:05:00 启动下载project数据
     */
    @Scheduled(cron = "0 5 0 * * ? ")
    public void wakaProject() {
        log.info("开始执行定时任务:下载project数据");
        this.updateWakaProject();
    }


    /**
     * 每天00:05:00 启动下载heart数据
     */
    @Scheduled(cron = "0 5 0 * * ? ")
    public void wakaHeart() {
        log.info("开始执行定时任务:下载heart数据");
        this.updateWakaHeart();
    }

    /**
     * 每天00:05:00 启动下载用户数据
     */
    @Scheduled(cron = "0 5 0 * * ? ")
    public void wakaUserInfo() {
        log.info("开始执行定时任务:下载heart数据");
        this.updateWakaTimeUserInfo();
    }


}
