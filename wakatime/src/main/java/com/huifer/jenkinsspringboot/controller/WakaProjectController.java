package com.huifer.jenkinsspringboot.controller;

import com.huifer.jenkinsspringboot.entity.RetResponse;
import com.huifer.jenkinsspringboot.entity.RetResult;
import com.huifer.jenkinsspringboot.entity.result.ProInfo;
import com.huifer.jenkinsspringboot.entity.result.ProjectWithUser;
import com.huifer.jenkinsspringboot.service.query.WakaTimeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 项目相关查询
 *
 * @Date: 2019-09-30
 */
@RestController
@RequestMapping("/wakatime/project")
public class WakaProjectController {


    @Autowired
    private WakaTimeQuery wakaTimeQuery;

    /**
     * 项目详情
     *
     * @param projectName
     * @return
     */
    @GetMapping("/{name}")
    public RetResult<Map<String, Map<String, ProjectWithUser>>> projectInfo(
            @PathVariable("name") String projectName
    ) throws Exception {
        return RetResponse.makeOKRsp(wakaTimeQuery.statisticsProjectByUser(projectName));
    }

    /**
     * 项目开发总计
     *
     * @return
     */
    @GetMapping("/sum")
    public RetResult<List<ProInfo>> projectSum() {
        return RetResponse.makeOKRsp(wakaTimeQuery.statisticsPro());

    }

    /**
     * 项目列表
     *
     * @return
     */
    @GetMapping("/list")
    public RetResult<List<String>> projectList() {
        return RetResponse.makeOKRsp(wakaTimeQuery.projectList());
    }

    /**
     * 添加一个项目
     *
     * @param projectName
     * @return
     */
    @PostMapping("/{name}")
    public RetResult<Object> addProject(
            @PathVariable("name") String projectName
    ) {
        wakaTimeQuery.insertProject(projectName);
        return RetResponse.makeOKRsp();
    }
}
