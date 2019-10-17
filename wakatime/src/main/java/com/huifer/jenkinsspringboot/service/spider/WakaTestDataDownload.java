package com.huifer.jenkinsspringboot.service.spider;

import com.huifer.jenkinsspringboot.entity.db.UserApiPO;
import com.huifer.jenkinsspringboot.entity.wakarest.HeartRest;
import com.huifer.jenkinsspringboot.mapper.UserApiPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 * 测试数据下载
 *
 * @author huifer
 * @date 2019-09-30
 */
@Service
public class WakaTestDataDownload {

    @Autowired
    WakaTimeWork wakaTimeWork;
    @Autowired
    private UserApiPOMapper userApiPOMapper;
    @Autowired
    private WakaSpider wakaSpider;

    public void history() {
        wakaTimeWork.historySeven();
    }


    public HashMap<String, List<HeartRest>> inst() {
        String[] ds = {
                "2019-09-21",
                "2019-09-22",
                "2019-09-23",
                "2019-09-24",
                "2019-09-25",
                "2019-09-26",
                "2019-09-27",
                "2019-09-28",
                "2019-09-29",
        };
        List<UserApiPO> userApiList = userApiPOMapper.findAll();

        HashMap<String, List<HeartRest>> res = new HashMap<>();

        for (String d : ds) {
            List<HeartRest> heartRests = new ArrayList<>();
            for (UserApiPO userInfo : userApiList) {
                List<HeartRest> heart = wakaSpider.heart(d, userInfo.getApiKey(), userInfo.getId());
                heartRests.addAll(heart);
            }
            res.put(d, heartRests);

        }
        return res;
    }


    public void insert() {
        String[] ds = {
                "2019-09-21",
                "2019-09-22",
                "2019-09-23",
                "2019-09-24",
                "2019-09-25",
                "2019-09-26",
                "2019-09-27",
                "2019-09-28",
                "2019-09-29",
        };
        List<UserApiPO> userApiList = userApiPOMapper.findAll();
        for (String d : ds) {
            for (UserApiPO userInfo : userApiList) {
                wakaSpider.durations(d, userInfo.getApiKey(), userInfo.getId());
            }

        }

    }
}
