package com.huifer.jenkinsspringboot.controller;

import com.huifer.jenkinsspringboot.entity.RetResponse;
import com.huifer.jenkinsspringboot.entity.RetResult;
import com.huifer.jenkinsspringboot.entity.db.SummaryProjectPO;
import com.huifer.jenkinsspringboot.entity.result.ProjectWithUser;
import com.huifer.jenkinsspringboot.service.query.WakaTimeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * waka 用户
 *
 * @author: huifer
 * @date: 2019-10-05
 */
@RestController
@RequestMapping("/wakatime/user")
public class WakaUserController {
    @Autowired
    private WakaTimeQuery wakaTimeQuery;

    @GetMapping("/list")
    public RetResult userList() {
        return RetResponse.makeOKRsp(wakaTimeQuery.findUsers());
    }

    @GetMapping("/{apiKey}")
    public RetResult userInfo(
            @PathVariable("apiKey") String apiKey
    ) {

        List<SummaryProjectPO> summaryProjectPOS = wakaTimeQuery.sumProject(apiKey);
        return RetResponse.makeOKRsp(summaryProjectPOS);
    }

    @GetMapping("/{apiKey}/{name}")
    public RetResult<HashMap<String, Map<String, ProjectWithUser>>> userProjectInfo(
            @PathVariable("apiKey") String apiKey,
            @PathVariable("name") String project
    ) {
        HashMap<String, HashMap<String, Map<String, ProjectWithUser>>> stringHashMapHashMap = wakaTimeQuery.statisticsUser(project, apiKey);
        return RetResponse.makeOKRsp(stringHashMapHashMap.get(apiKey));


    }
}
