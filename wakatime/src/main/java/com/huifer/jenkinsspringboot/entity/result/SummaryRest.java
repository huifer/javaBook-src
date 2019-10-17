package com.huifer.jenkinsspringboot.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 每日详细数据
 *
 * @author huifer
 * @date 2019-10-02
 */
@NoArgsConstructor
@Data
public class SummaryRest {

    /**
     * data : [{"categories":[{"digital":"10:07:41","hours":10,"minutes":7,"name":"Coding","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}],"dependencies":[{"digital":"3:02:25","hours":3,"minutes":2,"name":"springframework.beans","percent":17.1,"seconds":25,"text":"3 hrs 2 mins","total_seconds":10945.649},{"digital":"2:43:13","hours":2,"minutes":43,"name":"springframework.web","percent":15.3,"seconds":13,"text":"2 hrs 43 mins","total_seconds":9793.162},{"digital":"2:20:17","hours":2,"minutes":20,"name":"huifer.jenkinsspringboot","percent":13.15,"seconds":17,"text":"2 hrs 20 mins","total_seconds":8417.864},{"digital":"1:37:17","hours":1,"minutes":37,"name":"lombok.Data","percent":9.12,"seconds":17,"text":"1 hr 37 mins","total_seconds":5837.433},{"digital":"1:28:33","hours":1,"minutes":28,"name":"io.swagger","percent":8.3,"seconds":33,"text":"1 hr 28 mins","total_seconds":5313.23},{"digital":"1:13:28","hours":1,"minutes":13,"name":"springframework.stereotype","percent":6.89,"seconds":28,"text":"1 hr 13 mins","total_seconds":4408.341},{"digital":"1:10:51","hours":1,"minutes":10,"name":"springframework.http","percent":6.64,"seconds":51,"text":"1 hr 10 mins","total_seconds":4251.509},{"digital":"0:47:22","hours":0,"minutes":47,"name":"alibaba.fastjson","percent":4.44,"seconds":22,"text":"47 mins","total_seconds":2842.718},{"digital":"0:27:18","hours":0,"minutes":27,"name":"springframework.boot","percent":2.56,"seconds":18,"text":"27 mins","total_seconds":1638.813},{"digital":"0:26:36","hours":0,"minutes":26,"name":"lombok.extern","percent":2.49,"seconds":36,"text":"26 mins","total_seconds":1596.582},{"digital":"0:20:31","hours":0,"minutes":20,"name":"junit.Test","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:20:31","hours":0,"minutes":20,"name":"springframework.test","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:20:31","hours":0,"minutes":20,"name":"junit.runner","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:18:59","hours":0,"minutes":18,"name":"springframework.util","percent":1.78,"seconds":59,"text":"18 mins","total_seconds":1139.579},{"digital":"0:17:18","hours":0,"minutes":17,"name":"shands.senbo","percent":1.62,"seconds":18,"text":"17 mins","total_seconds":1038.088},{"digital":"0:17:18","hours":0,"minutes":17,"name":"github.wxpay","percent":1.62,"seconds":18,"text":"17 mins","total_seconds":1038.088},{"digital":"0:13:11","hours":0,"minutes":13,"name":"springframework.context","percent":1.24,"seconds":11,"text":"13 mins","total_seconds":791.044},{"digital":"0:06:23","hours":0,"minutes":6,"name":"springfox.documentation","percent":0.6,"seconds":23,"text":"6 mins","total_seconds":383.605},{"digital":"0:01:41","hours":0,"minutes":1,"name":"shands.gw","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.764},{"digital":"0:01:41","hours":0,"minutes":1,"name":"shands.core","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.729},{"digital":"0:01:41","hours":0,"minutes":1,"name":"security.krb5","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.491},{"digital":"0:01:17","hours":0,"minutes":1,"name":"lombok.NoArgsConstructor","percent":0.12,"seconds":17,"text":"1 min","total_seconds":77.97},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.highcharts.com/stock/highstock.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"app.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://rawgithub.com/phpepe/highcharts-regression/master/highcharts-regression.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.highcharts.com/stock/modules/exporting.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://rawgit.com/highcharts/highcharts/70f37e4c09f5fb5f6547b2bc6945b52c6afeb50e/js/themes/sand-signika.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.jquery.com/jquery-2.2.3.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:00:11","hours":0,"minutes":0,"name":"\"https://code.jquery.com/jquery-1.11.3.min.js\"","percent":0.02,"seconds":11,"text":"11 secs","total_seconds":11.073223},{"digital":"0:00:11","hours":0,"minutes":0,"name":"\"https://cdn.jsdelivr.net/lodash/4.11.2/lodash.core.min.js\"","percent":0.02,"seconds":11,"text":"11 secs","total_seconds":11.073223},{"digital":"0:00:07","hours":0,"minutes":0,"name":"junit.Assert","percent":0.01,"seconds":7,"text":"7 secs","total_seconds":7.518},{"digital":"0:00:00","hours":0,"minutes":0,"name":"slf4j.LoggerFactory","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.238},{"digital":"0:00:00","hours":0,"minutes":0,"name":"slf4j.Logger","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.238},{"digital":"0:00:00","hours":0,"minutes":0,"name":"baomidou.mybatisplus","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.211},{"digital":"0:00:00","hours":0,"minutes":0,"name":"springframework.transaction","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.176}],"editors":[{"digital":"7:59:01","hours":7,"minutes":59,"name":"IntelliJ","percent":78.83,"seconds":1,"text":"7 hrs 59 mins","total_seconds":28741.386114},{"digital":"2:08:40","hours":2,"minutes":8,"name":"VS Code","percent":21.17,"seconds":40,"text":"2 hrs 8 mins","total_seconds":7720.152886}],"grand_total":{"digital":"10:07","hours":10,"minutes":7,"text":"10 hrs 7 mins","total_seconds":36461.539},"languages":[{"digital":"4:57:32","hours":4,"minutes":57,"name":"Java","percent":48.96,"seconds":32,"text":"4 hrs 57 mins","total_seconds":17852.056},{"digital":"3:25:58","hours":3,"minutes":25,"name":"Markdown","percent":33.9,"seconds":58,"text":"3 hrs 25 mins","total_seconds":12358.946107},{"digital":"1:00:38","hours":1,"minutes":0,"name":"XML","percent":9.98,"seconds":38,"text":"1 hr","total_seconds":3638.269},{"digital":"0:38:25","hours":0,"minutes":38,"name":"YAML","percent":6.32,"seconds":25,"text":"38 mins","total_seconds":2305.692114},{"digital":"0:02:56","hours":0,"minutes":2,"name":"SQL","percent":0.48,"seconds":56,"text":"2 mins","total_seconds":176.691},{"digital":"0:01:11","hours":0,"minutes":1,"name":"HTML","percent":0.2,"seconds":11,"text":"1 min","total_seconds":71.603114},{"digital":"0:00:39","hours":0,"minutes":0,"name":"JavaScript","percent":0.11,"seconds":39,"text":"39 secs","total_seconds":39.380665},{"digital":"0:00:18","hours":0,"minutes":0,"name":"Bash","percent":0.05,"seconds":18,"text":"18 secs","total_seconds":18.901}],"machines":[{"digital":"10:07:41","hours":10,"machine_name_id":"ccb05e90-c552-41ae-ab00-ce1625c05414","minutes":7,"name":"zhulei","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}],"operating_systems":[{"digital":"10:07:41","hours":10,"minutes":7,"name":"Windows","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}],"projects":[{"digital":"7:35:47","hours":7,"minutes":35,"name":"jenkins-spring-boot","percent":75,"seconds":47,"text":"7 hrs 35 mins","total_seconds":27347.133114},{"digital":"2:08:40","hours":2,"minutes":8,"name":"rescuetime-again-gh-pages","percent":21.17,"seconds":40,"text":"2 hrs 8 mins","total_seconds":7720.152886},{"digital":"0:21:32","hours":0,"minutes":21,"name":"senbo","percent":3.54,"seconds":32,"text":"21 mins","total_seconds":1292.489},{"digital":"0:01:41","hours":0,"minutes":1,"name":"gw","percent":0.28,"seconds":41,"text":"1 min","total_seconds":101.764}],"range":{"date":"2019-09-30","end":"2019-09-30T15:59:59Z","start":"2019-09-29T16:00:00Z","text":"Mon Sep 30th 2019","timezone":"Asia/Shanghai"}}]
     * end : 2019-09-30T15:59:59Z
     * start : 2019-09-29T16:00:00Z
     */

    private Date end;
    private Date start;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * categories : [{"digital":"10:07:41","hours":10,"minutes":7,"name":"Coding","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}]
         * dependencies : [{"digital":"3:02:25","hours":3,"minutes":2,"name":"springframework.beans","percent":17.1,"seconds":25,"text":"3 hrs 2 mins","total_seconds":10945.649},{"digital":"2:43:13","hours":2,"minutes":43,"name":"springframework.web","percent":15.3,"seconds":13,"text":"2 hrs 43 mins","total_seconds":9793.162},{"digital":"2:20:17","hours":2,"minutes":20,"name":"huifer.jenkinsspringboot","percent":13.15,"seconds":17,"text":"2 hrs 20 mins","total_seconds":8417.864},{"digital":"1:37:17","hours":1,"minutes":37,"name":"lombok.Data","percent":9.12,"seconds":17,"text":"1 hr 37 mins","total_seconds":5837.433},{"digital":"1:28:33","hours":1,"minutes":28,"name":"io.swagger","percent":8.3,"seconds":33,"text":"1 hr 28 mins","total_seconds":5313.23},{"digital":"1:13:28","hours":1,"minutes":13,"name":"springframework.stereotype","percent":6.89,"seconds":28,"text":"1 hr 13 mins","total_seconds":4408.341},{"digital":"1:10:51","hours":1,"minutes":10,"name":"springframework.http","percent":6.64,"seconds":51,"text":"1 hr 10 mins","total_seconds":4251.509},{"digital":"0:47:22","hours":0,"minutes":47,"name":"alibaba.fastjson","percent":4.44,"seconds":22,"text":"47 mins","total_seconds":2842.718},{"digital":"0:27:18","hours":0,"minutes":27,"name":"springframework.boot","percent":2.56,"seconds":18,"text":"27 mins","total_seconds":1638.813},{"digital":"0:26:36","hours":0,"minutes":26,"name":"lombok.extern","percent":2.49,"seconds":36,"text":"26 mins","total_seconds":1596.582},{"digital":"0:20:31","hours":0,"minutes":20,"name":"junit.Test","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:20:31","hours":0,"minutes":20,"name":"springframework.test","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:20:31","hours":0,"minutes":20,"name":"junit.runner","percent":1.92,"seconds":31,"text":"20 mins","total_seconds":1231.374},{"digital":"0:18:59","hours":0,"minutes":18,"name":"springframework.util","percent":1.78,"seconds":59,"text":"18 mins","total_seconds":1139.579},{"digital":"0:17:18","hours":0,"minutes":17,"name":"shands.senbo","percent":1.62,"seconds":18,"text":"17 mins","total_seconds":1038.088},{"digital":"0:17:18","hours":0,"minutes":17,"name":"github.wxpay","percent":1.62,"seconds":18,"text":"17 mins","total_seconds":1038.088},{"digital":"0:13:11","hours":0,"minutes":13,"name":"springframework.context","percent":1.24,"seconds":11,"text":"13 mins","total_seconds":791.044},{"digital":"0:06:23","hours":0,"minutes":6,"name":"springfox.documentation","percent":0.6,"seconds":23,"text":"6 mins","total_seconds":383.605},{"digital":"0:01:41","hours":0,"minutes":1,"name":"shands.gw","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.764},{"digital":"0:01:41","hours":0,"minutes":1,"name":"shands.core","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.729},{"digital":"0:01:41","hours":0,"minutes":1,"name":"security.krb5","percent":0.16,"seconds":41,"text":"1 min","total_seconds":101.491},{"digital":"0:01:17","hours":0,"minutes":1,"name":"lombok.NoArgsConstructor","percent":0.12,"seconds":17,"text":"1 min","total_seconds":77.97},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.highcharts.com/stock/highstock.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"app.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://rawgithub.com/phpepe/highcharts-regression/master/highcharts-regression.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.highcharts.com/stock/modules/exporting.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://rawgit.com/highcharts/highcharts/70f37e4c09f5fb5f6547b2bc6945b52c6afeb50e/js/themes/sand-signika.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:01:00","hours":0,"minutes":1,"name":"\"https://code.jquery.com/jquery-2.2.3.min.js\"","percent":0.09,"seconds":0,"text":"1 min","total_seconds":60.529891},{"digital":"0:00:11","hours":0,"minutes":0,"name":"\"https://code.jquery.com/jquery-1.11.3.min.js\"","percent":0.02,"seconds":11,"text":"11 secs","total_seconds":11.073223},{"digital":"0:00:11","hours":0,"minutes":0,"name":"\"https://cdn.jsdelivr.net/lodash/4.11.2/lodash.core.min.js\"","percent":0.02,"seconds":11,"text":"11 secs","total_seconds":11.073223},{"digital":"0:00:07","hours":0,"minutes":0,"name":"junit.Assert","percent":0.01,"seconds":7,"text":"7 secs","total_seconds":7.518},{"digital":"0:00:00","hours":0,"minutes":0,"name":"slf4j.LoggerFactory","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.238},{"digital":"0:00:00","hours":0,"minutes":0,"name":"slf4j.Logger","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.238},{"digital":"0:00:00","hours":0,"minutes":0,"name":"baomidou.mybatisplus","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.211},{"digital":"0:00:00","hours":0,"minutes":0,"name":"springframework.transaction","percent":0,"seconds":0,"text":"0 secs","total_seconds":0.176}]
         * editors : [{"digital":"7:59:01","hours":7,"minutes":59,"name":"IntelliJ","percent":78.83,"seconds":1,"text":"7 hrs 59 mins","total_seconds":28741.386114},{"digital":"2:08:40","hours":2,"minutes":8,"name":"VS Code","percent":21.17,"seconds":40,"text":"2 hrs 8 mins","total_seconds":7720.152886}]
         * grand_total : {"digital":"10:07","hours":10,"minutes":7,"text":"10 hrs 7 mins","total_seconds":36461.539}
         * languages : [{"digital":"4:57:32","hours":4,"minutes":57,"name":"Java","percent":48.96,"seconds":32,"text":"4 hrs 57 mins","total_seconds":17852.056},{"digital":"3:25:58","hours":3,"minutes":25,"name":"Markdown","percent":33.9,"seconds":58,"text":"3 hrs 25 mins","total_seconds":12358.946107},{"digital":"1:00:38","hours":1,"minutes":0,"name":"XML","percent":9.98,"seconds":38,"text":"1 hr","total_seconds":3638.269},{"digital":"0:38:25","hours":0,"minutes":38,"name":"YAML","percent":6.32,"seconds":25,"text":"38 mins","total_seconds":2305.692114},{"digital":"0:02:56","hours":0,"minutes":2,"name":"SQL","percent":0.48,"seconds":56,"text":"2 mins","total_seconds":176.691},{"digital":"0:01:11","hours":0,"minutes":1,"name":"HTML","percent":0.2,"seconds":11,"text":"1 min","total_seconds":71.603114},{"digital":"0:00:39","hours":0,"minutes":0,"name":"JavaScript","percent":0.11,"seconds":39,"text":"39 secs","total_seconds":39.380665},{"digital":"0:00:18","hours":0,"minutes":0,"name":"Bash","percent":0.05,"seconds":18,"text":"18 secs","total_seconds":18.901}]
         * machines : [{"digital":"10:07:41","hours":10,"machine_name_id":"ccb05e90-c552-41ae-ab00-ce1625c05414","minutes":7,"name":"zhulei","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}]
         * operating_systems : [{"digital":"10:07:41","hours":10,"minutes":7,"name":"Windows","percent":100,"seconds":41,"text":"10 hrs 7 mins","total_seconds":36461.539}]
         * projects : [{"digital":"7:35:47","hours":7,"minutes":35,"name":"jenkins-spring-boot","percent":75,"seconds":47,"text":"7 hrs 35 mins","total_seconds":27347.133114},{"digital":"2:08:40","hours":2,"minutes":8,"name":"rescuetime-again-gh-pages","percent":21.17,"seconds":40,"text":"2 hrs 8 mins","total_seconds":7720.152886},{"digital":"0:21:32","hours":0,"minutes":21,"name":"senbo","percent":3.54,"seconds":32,"text":"21 mins","total_seconds":1292.489},{"digital":"0:01:41","hours":0,"minutes":1,"name":"gw","percent":0.28,"seconds":41,"text":"1 min","total_seconds":101.764}]
         * range : {"date":"2019-09-30","end":"2019-09-30T15:59:59Z","start":"2019-09-29T16:00:00Z","text":"Mon Sep 30th 2019","timezone":"Asia/Shanghai"}
         */

        private GrandTotalBean grandTotal;
        private RangeBean range;
        private List<CategoriesBean> categories;
        private List<DependenciesBean> dependencies;
        private List<EditorsBean> editors;
        private List<LanguagesBean> languages;
        private List<MachinesBean> machines;
        private List<OperatingSystemsBean> operatingSystems;
        private List<ProjectsBean> projects;

        @NoArgsConstructor
        @Data
        public static class GrandTotalBean {
            /**
             * digital : 10:07
             * hours : 10
             * minutes : 7
             * text : 10 hrs 7 mins
             * total_seconds : 36461.539
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String text;
            private BigDecimal totalSeconds;
        }


        @NoArgsConstructor
        @Data
        public static class RangeBean {
            /**
             * date : 2019-09-30
             * end : 2019-09-30T15:59:59Z
             * start : 2019-09-29T16:00:00Z
             * text : Mon Sep 30th 2019
             * timezone : Asia/Shanghai
             */

            private String date;
            private Date end;
            private Date start;
            private String text;
            private String timezone;
        }

        @NoArgsConstructor
        @Data
        public static class CategoriesBean {
            /**
             * digital : 10:07:41
             * hours : 10
             * minutes : 7
             * name : Coding
             * percent : 100
             * seconds : 41
             * text : 10 hrs 7 mins
             * total_seconds : 36461.539
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class DependenciesBean {
            /**
             * digital : 3:02:25
             * hours : 3
             * minutes : 2
             * name : springframework.beans
             * percent : 17.1
             * seconds : 25
             * text : 3 hrs 2 mins
             * total_seconds : 10945.649
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class EditorsBean {
            /**
             * digital : 7:59:01
             * hours : 7
             * minutes : 59
             * name : IntelliJ
             * percent : 78.83
             * seconds : 1
             * text : 7 hrs 59 mins
             * total_seconds : 28741.386114
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class LanguagesBean {
            /**
             * digital : 4:57:32
             * hours : 4
             * minutes : 57
             * name : Java
             * percent : 48.96
             * seconds : 32
             * text : 4 hrs 57 mins
             * total_seconds : 17852.056
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class MachinesBean {
            /**
             * digital : 10:07:41
             * hours : 10
             * machine_name_id : ccb05e90-c552-41ae-ab00-ce1625c05414
             * minutes : 7
             * name : zhulei
             * percent : 100
             * seconds : 41
             * text : 10 hrs 7 mins
             * total_seconds : 36461.539
             */

            private String digital;
            private BigDecimal hours;
            private String machineNameId;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class OperatingSystemsBean {
            /**
             * digital : 10:07:41
             * hours : 10
             * minutes : 7
             * name : Windows
             * percent : 100
             * seconds : 41
             * text : 10 hrs 7 mins
             * total_seconds : 36461.539
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class ProjectsBean {
            /**
             * digital : 7:35:47
             * hours : 7
             * minutes : 35
             * name : jenkins-spring-boot
             * percent : 75
             * seconds : 47
             * text : 7 hrs 35 mins
             * total_seconds : 27347.133114
             */

            private String digital;
            private BigDecimal hours;
            private BigDecimal minutes;
            private String name;
            private BigDecimal percent;
            private BigDecimal seconds;
            private String text;
            private BigDecimal totalSeconds;
        }
    }
}
