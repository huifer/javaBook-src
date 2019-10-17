package com.huifer.jenkinsspringboot.controller;

import com.huifer.jenkinsspringboot.entity.db.SummaryProjectPO;
import com.huifer.jenkinsspringboot.entity.db.UserApiPO;
import com.huifer.jenkinsspringboot.entity.result.ProInfo;
import com.huifer.jenkinsspringboot.entity.result.ProjectWithUser;
import com.huifer.jenkinsspringboot.service.echart.WakaEchartService;
import com.huifer.jenkinsspringboot.service.query.WakaTimeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-04
 */
@Controller
@RequestMapping("/ui")
public class UIController {

    @Autowired
    private WakaTimeQuery wakaTimeQuery;

    @Autowired
    private WakaEchartService wakaEchartService;


    @RequestMapping("/user/{apiKey}/{project}")
    public String userProject(
            Model m,
            @PathVariable("apiKey") String apiKey,
            @PathVariable("project") String project

    ) {
        HashMap<String, HashMap<String, Map<String, ProjectWithUser>>> stringHashMapHashMap = wakaTimeQuery.statisticsUser(project, apiKey);
        m.addAttribute("apiKey", apiKey);
        m.addAttribute("project", project);
        m.addAttribute("map", stringHashMapHashMap.get(apiKey).get(project));
        Map<String, ProjectWithUser> stringProjectWithUserMap = stringHashMapHashMap.get(apiKey).get(project);

        List<String> x = new ArrayList<>();
        List<BigDecimal> y = new ArrayList<>();
        List<Object[]> data = new ArrayList<>();

        stringProjectWithUserMap.forEach((k, v) -> {
            x.add(k);
            y.add(v.getTotalSeconds());
            data.add(new Object[]{k, v.getTotalSeconds()});

        });
        m.addAttribute("x", x);
        m.addAttribute("y", y);
        m.addAttribute("year", data);

        m.addAttribute("max_sec", y.stream().max(BigDecimal::compareTo).get());

        return "user_info_prj";
    }

    @RequestMapping("/user/{apiKey}")
    public String userInfo(
            Model m,
            @PathVariable("apiKey") String apiKey
    ) {
        HashMap<String, HashMap<String, Map<String, ProjectWithUser>>> stringHashMapHashMap = wakaTimeQuery.statisticsUser("", apiKey);

        m.addAttribute("apiKey", apiKey);
        m.addAttribute("pro", wakaTimeQuery.projectAll());

        List<SummaryProjectPO> summaryProjectPOS = wakaTimeQuery.sumProject(apiKey);
        // 个人开发时间饼图

        List<Map<String, Object>> h = new ArrayList<>();
        for (SummaryProjectPO summaryProjectPO : summaryProjectPOS) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", summaryProjectPO.getTotalSeconds());
            map.put("name", summaryProjectPO.getName());
            h.add(map);
        }
        m.addAttribute("data", h);

        return "user_info";
    }

    @RequestMapping("/user")
    public String userTable(
            Model m
    ) {
        List<UserApiPO> userApiPOS = wakaTimeQuery.userInfo();
        m.addAttribute("userInfo", userApiPOS);
        return "user_table";
    }

    @RequestMapping("/pro")
    public String pro(
            Model m
    ) {
        List<ProInfo> proInfos = wakaTimeQuery.statisticsPro();
        m.addAttribute("list", proInfos);

        List<String> x = new ArrayList<>();
        List<BigDecimal> y = new ArrayList<>();

        proInfos.forEach(e -> {
            x.add(e.getName());
            y.add(e.getTotalSeconds());
        });

        m.addAttribute("x", x);
        m.addAttribute("y", y);
        return "ProjectTable";
    }


    @RequestMapping("/pro_info/{name}")
    public String proInfo(Model m,
                          @PathVariable("name") String name) throws Exception {
        Map<String, Map<String, ProjectWithUser>> stringMapMap = wakaTimeQuery.statisticsProjectByUser(name);
        m.addAttribute("proj_name", name);
        m.addAttribute("map", stringMapMap.get(name));

        m.addAttribute("x", wakaEchartService.project(name).getX());
        m.addAttribute("y", wakaEchartService.project(name).getY());

        return "proInfo";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
