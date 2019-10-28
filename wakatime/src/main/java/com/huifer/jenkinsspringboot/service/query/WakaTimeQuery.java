package com.huifer.jenkinsspringboot.service.query;

import com.huifer.jenkinsspringboot.entity.db.ProjectPO;
import com.huifer.jenkinsspringboot.entity.db.SummaryProjectPO;
import com.huifer.jenkinsspringboot.entity.db.UserApiPO;
import com.huifer.jenkinsspringboot.entity.result.DayInfo;
import com.huifer.jenkinsspringboot.entity.result.ProInfo;
import com.huifer.jenkinsspringboot.entity.result.ProjectWithUser;
import com.huifer.jenkinsspringboot.exception.ServiceException;
import com.huifer.jenkinsspringboot.mapper.*;
import com.huifer.jenkinsspringboot.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 描述:
 * waka time 查询逻辑
 *
 * @author huifer
 * @date 2019-10-01
 */
@Service
public class WakaTimeQuery {

    @Autowired
    private ProjectPOMapper projectMapper;
    @Autowired
    private HistorySevenPOMapper historySevenMapper;

    @Autowired
    private UserApiPOMapper userApiMapper;

    @Autowired
    private WakaUserinfoMapper userinfoMapper;


    @Autowired
    private SummaryProjectPOMapper summaryProjectPOMapper;

    @Autowired
    private UserApiPOMapper userApiPOMapper;

    public static <T> Map<String, T> sortMapByKey(Map<String, T> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, T> sortMap = new TreeMap<>();

        sortMap.putAll(map);

        return sortMap;
    }

    /**
     * 根据api key 和 project name 统计项目时间
     *
     * @param apiKey
     */
    public List<SummaryProjectPO> sumProject(String apiKey) {
        List<SummaryProjectPO> su = summaryProjectPOMapper.sumApiAndPro(apiKey);
        return su;
    }

    public List<ProjectPO> projectAll() {
        return projectMapper.findAll();
    }

    public List<UserApiPO> userInfo() {
        return userApiPOMapper.findAll();
    }

    public List<ProInfo> statisticsPro() {
        return summaryProjectPOMapper.sumProject();
    }


    /**
     * 查询项目的开发时间
     *
     * @return
     */
    public Map<String, List<DayInfo>> statisticsDay() {
        List<SummaryProjectPO> summaryProjectPOS = summaryProjectPOMapper.groupByDaySumSeconds();

        List<String> days = DateUtils.getStart2End();
        HashMap<String, List<DayInfo>> res = new HashMap<>();
        for (String day : days) {
            List<DayInfo> dayInfos = new ArrayList<>();
            for (SummaryProjectPO summaryProjectPO : summaryProjectPOS) {

                if (summaryProjectPO.getDay().equals(day)) {
                    DayInfo dayInfo = new DayInfo();
                    dayInfo.setDate(summaryProjectPO.getDay());
                    dayInfo.setProjectName(summaryProjectPO.getName());
                    dayInfo.setText("");
                    dayInfo.setTotalSeconds(summaryProjectPO.getTotalSeconds());
                    dayInfo.setTimestamp(DateUtils.day2timestamp(summaryProjectPO.getDay()));

                    dayInfos.add(dayInfo);
                } else {
                    DayInfo dayInfo = new DayInfo();
                    dayInfo.setDate("");
                    dayInfo.setProjectName(summaryProjectPO.getName());
                    dayInfo.setText("");
                    dayInfo.setTotalSeconds(new BigDecimal("0"));
                    dayInfo.setTimestamp(DateUtils.day2timestamp(day));
                    dayInfos.add(dayInfo);

                }


            }
            res.put(day, dayInfos);
        }

        Map<String, List<DayInfo>> stringListMap = sortMapByKey(res);
        return stringListMap;
    }

    /**
     * 个人项目时间统计
     * 用户名 ： 项目信息
     *
     * @return
     */
    public HashMap<String, HashMap<String, Map<String, ProjectWithUser>>> statisticsUser(
            String projectName,
            String apiKey
    ) {

        UserApiPO user = userApiMapper.findByApiKey(apiKey);
        if (user == null) {
            throw ServiceException.DATA_ERRO;
        } else {

            List<ProjectPO> projects = projectMapper.findByName(projectName);
            List<String> days = DateUtils.getStart2End();

            HashMap<String, HashMap<String, Map<String, ProjectWithUser>>> res = new HashMap<>();

            HashMap<String, Map<String, ProjectWithUser>> rs = new HashMap<>();
            for (ProjectPO project : projects) {
                Map<String, ProjectWithUser> proj = new HashMap<>();
                dayProj(days, project, proj);
                rs.put(project.getProjectName(), sortMapByKey(proj));
            }
            res.put(apiKey, rs);
            if (rs.isEmpty()) {
                throw ServiceException.DATA_ERRO;
            }
            return res;
        }
    }

    private void dayProj(List<String> days, ProjectPO project, Map<String, ProjectWithUser> proj) {
        for (String day : days) {
            SummaryProjectPO byProject = summaryProjectPOMapper.findByProject(day, project.getProjectName());
            ProjectWithUser projectWithUser = new ProjectWithUser();
            initProjectWithUser(day, byProject, projectWithUser);
            proj.put(day, projectWithUser);
        }
    }


    /**
     * 项目: 日期 : 开发时间
     *
     * @return
     */
    public Map<String, Map<String, ProjectWithUser>> statisticsProjectByUser(String projectName) {
        List<ProjectPO> projects = projectMapper.findByName(projectName);
        if (projects.isEmpty()) {
            throw ServiceException.DATA_ERRO;
        }

        List<String> days = DateUtils.getStart2End();
        Map<String, Map<String, ProjectWithUser>> res = new HashMap<>();
        for (ProjectPO project : projects) {
            Map<String, ProjectWithUser> proj = new HashMap<>();
            dayProj(days, project, proj);
            res.put(project.getProjectName(), sortMapByKey(proj));
        }

        return sortMapByKey(res);
    }

    private void initProjectWithUser(String day, SummaryProjectPO byProject, ProjectWithUser projectWithUser) {
        if (byProject != null) {
            projectWithUser.setProjectNanme(byProject.getName());
            projectWithUser.setDay(day);
            projectWithUser.setHours(byProject.getHours());
            projectWithUser.setMinutes(byProject.getMinutes());
            projectWithUser.setText(byProject.getText());
            projectWithUser.setTotalSeconds(byProject.getTotalSeconds());
            projectWithUser.setTimestamp(DateUtils.day2timestamp(day));

        } else {
            projectWithUser.setDay(day);
            projectWithUser.setHours(new BigDecimal(0));
            projectWithUser.setMinutes(new BigDecimal(0));
            projectWithUser.setTotalSeconds(new BigDecimal(0));
            projectWithUser.setTimestamp(DateUtils.day2timestamp(day));
        }
    }

    /**
     * 所有可知项目
     *
     * @return
     */
    public List<String> projectList() {
        return summaryProjectPOMapper.findProject();
    }

    /**
     * 添加一个项目
     *
     * @param name
     */
    public void insertProject(String name) {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setProjectName(name);
        projectMapper.insert(projectPO);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<UserApiPO> findUsers() {
        return userApiMapper.findAll();
    }
}
