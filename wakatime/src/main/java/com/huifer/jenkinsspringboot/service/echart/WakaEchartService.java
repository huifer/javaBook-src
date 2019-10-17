package com.huifer.jenkinsspringboot.service.echart;

import com.huifer.jenkinsspringboot.entity.result.EchartOption;
import com.huifer.jenkinsspringboot.entity.result.ProjectWithUser;
import com.huifer.jenkinsspringboot.service.query.WakaTimeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-04
 */
@Service
public class WakaEchartService {
    @Autowired
    private WakaTimeQuery wakaTimeQuery;

    public EchartOption project(String name) {
        Map<String, Map<String, ProjectWithUser>> stringMapMap = wakaTimeQuery.statisticsProjectByUser(name);
        Map<String, ProjectWithUser> stringProjectWithUserMap = stringMapMap.get(name);

        List<BigDecimal> y = new ArrayList<>();
        List<String> x = new ArrayList<>();
        stringProjectWithUserMap.forEach(
                (k, v) -> {
                    BigDecimal totalSeconds = v.getTotalSeconds();
                    y.add(totalSeconds);
                    x.add(k);
                }
        );
        EchartOption echartOption = new EchartOption();
        echartOption.setX(x);
        echartOption.setY(y);

        return echartOption;
    }


}
