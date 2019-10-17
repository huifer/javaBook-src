package com.huifer.jenkinsspringboot.entity.wakarest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-09-30
 */
@Data
@NoArgsConstructor
public class HistorySeven {

    /**
     * data : {"best_day":{"created_at":"2019-09-24T02:48:12Z","date":"2019-09-29","id":"0a5c14d0-ad1f-4511-871a-7c5758f48f58","modified_at":"2019-09-29T19:07:28Z","text":"9 hrs 50 mins","total_seconds":35420.84},"categories":[{"digital":"29:32","hours":29,"minutes":32,"name":"Coding","percent":100,"text":"29 hrs 32 mins","total_seconds":106335.443}],"created_at":"2019-09-24T02:38:28Z","daily_average":20571,"daily_average_including_other_language":21267,"days_including_holidays":7,"days_minus_holidays":5,"dependencies":[{"digital":"9:20","hours":9,"minutes":20,"name":"springframework.web","percent":10.96,"text":"9 hrs 20 mins","total_seconds":33616.164219},{"digital":"8:09","hours":8,"minutes":9,"name":"shands.core","percent":9.58,"text":"8 hrs 9 mins","total_seconds":29376.323},{"digital":"7:57","hours":7,"minutes":57,"name":"springframework.stereotype","percent":9.34,"text":"7 hrs 57 mins","total_seconds":28653.296968},{"digital":"6:56","hours":6,"minutes":56,"name":"shands.senbo","percent":8.15,"text":"6 hrs 56 mins","total_seconds":24996.167},{"digital":"6:27","hours":6,"minutes":27,"name":"springframework.beans","percent":7.58,"text":"6 hrs 27 mins","total_seconds":23255.110717},{"digital":"5:47","hours":5,"minutes":47,"name":"slf4j.Logger","percent":6.79,"text":"5 hrs 47 mins","total_seconds":20830.83},{"digital":"5:46","hours":5,"minutes":46,"name":"slf4j.LoggerFactory","percent":6.78,"text":"5 hrs 46 mins","total_seconds":20774.378},{"digital":"5:28","hours":5,"minutes":28,"name":"baomidou.mybatisplus","percent":6.43,"text":"5 hrs 28 mins","total_seconds":19725.119},{"digital":"2:59","hours":2,"minutes":59,"name":"lombok.extern","percent":3.52,"text":"2 hrs 59 mins","total_seconds":10789.623486},{"digital":"2:32","hours":2,"minutes":32,"name":"springframework.util","percent":2.98,"text":"2 hrs 32 mins","total_seconds":9141.571232},{"digital":"2:31","hours":2,"minutes":31,"name":"springframework.http","percent":2.96,"text":"2 hrs 31 mins","total_seconds":9078.305398},{"digital":"2:26","hours":2,"minutes":26,"name":"sun.mail","percent":2.87,"text":"2 hrs 26 mins","total_seconds":8802.83},{"digital":"2:24","hours":2,"minutes":24,"name":"springframework.cloud","percent":2.83,"text":"2 hrs 24 mins","total_seconds":8662.6},{"digital":"2:07","hours":2,"minutes":7,"name":"springframework.boot","percent":2.49,"text":"2 hrs 7 mins","total_seconds":7638.656292},{"digital":"1:38","hours":1,"minutes":38,"name":"github.wxpay","percent":1.93,"text":"1 hr 38 mins","total_seconds":5929.278},{"digital":"1:27","hours":1,"minutes":27,"name":"springframework.scheduling","percent":1.7,"text":"1 hr 27 mins","total_seconds":5220.355806},{"digital":"1:23","hours":1,"minutes":23,"name":"junit.Test","percent":1.64,"text":"1 hr 23 mins","total_seconds":5024.66381},{"digital":"1:08","hours":1,"minutes":8,"name":"shands.gw","percent":1.35,"text":"1 hr 8 mins","total_seconds":4128.925},{"digital":"1:07","hours":1,"minutes":7,"name":"huifer.jenkinsspringboot","percent":1.32,"text":"1 hr 7 mins","total_seconds":4045.958398},{"digital":"0:55","hours":0,"minutes":55,"name":"alibaba.fastjson","percent":1.09,"text":"55 mins","total_seconds":3356.424859},{"digital":"0:51","hours":0,"minutes":51,"name":"junit.runner","percent":1.01,"text":"51 mins","total_seconds":3109.17181},{"digital":"0:49","hours":0,"minutes":49,"name":"springframework.test","percent":0.97,"text":"49 mins","total_seconds":2970.25581},{"digital":"0:31","hours":0,"minutes":31,"name":"springframework.data","percent":0.61,"text":"31 mins","total_seconds":1878.91},{"digital":"0:24","hours":0,"minutes":24,"name":"junit.Assert","percent":0.48,"text":"24 mins","total_seconds":1486.507},{"digital":"0:22","hours":0,"minutes":22,"name":"springframework.context","percent":0.45,"text":"22 mins","total_seconds":1375.621672},{"digital":"0:21","hours":0,"minutes":21,"name":"huifer.happy","percent":0.42,"text":"21 mins","total_seconds":1280.456},{"digital":"0:20","hours":0,"minutes":20,"name":"springframework.transaction","percent":0.39,"text":"20 mins","total_seconds":1211.066936},{"digital":"0:17","hours":0,"minutes":17,"name":"springframework.format","percent":0.35,"text":"17 mins","total_seconds":1071.699},{"digital":"0:15","hours":0,"minutes":15,"name":"java.beans.BeanProperty","percent":0.31,"text":"15 mins","total_seconds":953.892},{"digital":"0:13","hours":0,"minutes":13,"name":"wf2311.wakatime","percent":0.26,"text":"13 mins","total_seconds":812.345502},{"digital":"0:13","hours":0,"minutes":13,"name":"apache.ibatis","percent":0.26,"text":"13 mins","total_seconds":787.87},{"digital":"0:11","hours":0,"minutes":11,"name":"google.zxing","percent":0.22,"text":"11 mins","total_seconds":682.355},{"digital":"0:11","hours":0,"minutes":11,"name":"sf.json","percent":0.22,"text":"11 mins","total_seconds":682.355},{"digital":"0:10","hours":0,"minutes":10,"name":"springframework.validation","percent":0.2,"text":"10 mins","total_seconds":618.07},{"digital":"0:09","hours":0,"minutes":9,"name":"cn.hutool","percent":0.19,"text":"9 mins","total_seconds":579.8},{"digital":"0:07","hours":0,"minutes":7,"name":"sun.org","percent":0.15,"text":"7 mins","total_seconds":470.114},{"digital":"0:05","hours":0,"minutes":5,"name":"jboss.shrinkwrap","percent":0.11,"text":"5 mins","total_seconds":341.76},{"digital":"0:05","hours":0,"minutes":5,"name":"jboss.arquillian","percent":0.11,"text":"5 mins","total_seconds":341.76},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Data","percent":0.1,"text":"4 mins","total_seconds":292.54751},{"digital":"0:04","hours":0,"minutes":4,"name":"apache.commons","percent":0.09,"text":"4 mins","total_seconds":289.19818},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.ToString","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Setter","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Getter","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:03","hours":0,"minutes":3,"name":"apache.http","percent":0.07,"text":"3 mins","total_seconds":216.637},{"digital":"0:03","hours":0,"minutes":3,"name":"security.krb5","percent":0.06,"text":"3 mins","total_seconds":190.52},{"digital":"0:03","hours":0,"minutes":3,"name":"ch.qos","percent":0.06,"text":"3 mins","total_seconds":187.13},{"digital":"0:02","hours":0,"minutes":2,"name":"aspectj.lang","percent":0.06,"text":"2 mins","total_seconds":173.642},{"digital":"0:01","hours":0,"minutes":1,"name":"springframework.jdbc","percent":0.04,"text":"1 min","total_seconds":115.949902},{"digital":"0:01","hours":0,"minutes":1,"name":"springframework.aop","percent":0.04,"text":"1 min","total_seconds":109.218},{"digital":"0:01","hours":0,"minutes":1,"name":"fasterxml.jackson","percent":0.03,"text":"1 min","total_seconds":106.683325},{"digital":"0:01","hours":0,"minutes":1,"name":"shands.gateway","percent":0.03,"text":"1 min","total_seconds":76.918},{"digital":"0:01","hours":0,"minutes":1,"name":"thoughtworks.xstream","percent":0.02,"text":"1 min","total_seconds":62.638},{"digital":"0:01","hours":0,"minutes":1,"name":"bocpay.BocUtil","percent":0.02,"text":"1 min","total_seconds":62.638},{"digital":"0:00","hours":0,"minutes":0,"name":"jodd.http","percent":0.01,"text":"0 secs","total_seconds":33.643074},{"digital":"0:00","hours":0,"minutes":0,"name":"\"./js/lib.336055b3.js\"","percent":0.01,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"\"./js/app.c881f418.js\"","percent":0.01,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"netflix.zuul","percent":0.01,"text":"0 secs","total_seconds":22.253},{"digital":"0:00","hours":0,"minutes":0,"name":"lombok","percent":0,"text":"0 secs","total_seconds":14.579},{"digital":"0:00","hours":0,"minutes":0,"name":"io.jsonwebtoken","percent":0,"text":"0 secs","total_seconds":11.564},{"digital":"0:00","hours":0,"minutes":0,"name":"io.lettuce","percent":0,"text":"0 secs","total_seconds":11.452},{"digital":"0:00","hours":0,"minutes":0,"name":"redis.clients","percent":0,"text":"0 secs","total_seconds":11.452},{"digital":"0:00","hours":0,"minutes":0,"name":"example.demo","percent":0,"text":"0 secs","total_seconds":10.471},{"digital":"0:00","hours":0,"minutes":0,"name":"springframework.amqp","percent":0,"text":"0 secs","total_seconds":8.571},{"digital":"0:00","hours":0,"minutes":0,"name":"wf2311.jfeng","percent":0,"text":"0 secs","total_seconds":6.731902},{"digital":"0:00","hours":0,"minutes":0,"name":"springframework.core","percent":0,"text":"0 secs","total_seconds":5.755}],"editors":[{"digital":"29:10","hours":29,"minutes":10,"name":"IntelliJ","percent":98.77,"text":"29 hrs 10 mins","total_seconds":105030.099452},{"digital":"0:21","hours":0,"minutes":21,"name":"VS Code","percent":1.23,"text":"21 mins","total_seconds":1305.343548}],"end":"2019-09-29T15:59:59Z","holidays":2,"human_readable_daily_average":"5 hrs 42 mins","human_readable_daily_average_including_other_language":"5 hrs 54 mins","human_readable_total":"28 hrs 34 mins","human_readable_total_including_other_language":"29 hrs 32 mins","id":"103ff3cb-d8df-4af4-991d-5022d50c9c36","is_already_updating":false,"is_coding_activity_visible":false,"is_other_usage_visible":false,"is_stuck":false,"is_up_to_date":true,"languages":[{"digital":"24:39","hours":24,"minutes":39,"name":"Java","percent":83.49,"text":"24 hrs 39 mins","total_seconds":88781.850698},{"digital":"1:40","hours":1,"minutes":40,"name":"YAML","percent":5.65,"text":"1 hr 40 mins","total_seconds":6007.970564},{"digital":"0:58","hours":0,"minutes":58,"name":"Other","percent":3.27,"text":"58 mins","total_seconds":3480.782},{"digital":"0:51","hours":0,"minutes":51,"name":"XML","percent":2.89,"text":"51 mins","total_seconds":3076.487},{"digital":"0:32","hours":0,"minutes":32,"name":"SQL","percent":1.86,"text":"32 mins","total_seconds":1976.706},{"digital":"0:16","hours":0,"minutes":16,"name":"Text","percent":0.93,"text":"16 mins","total_seconds":984.803},{"digital":"0:15","hours":0,"minutes":15,"name":"Scala","percent":0.9,"text":"15 mins","total_seconds":953.892},{"digital":"0:09","hours":0,"minutes":9,"name":"Markdown","percent":0.54,"text":"9 mins","total_seconds":569.705738},{"digital":"0:02","hours":0,"minutes":2,"name":"Properties","percent":0.16,"text":"2 mins","total_seconds":174.046},{"digital":"0:02","hours":0,"minutes":2,"name":"Groovy","percent":0.15,"text":"2 mins","total_seconds":162.109},{"digital":"0:02","hours":0,"minutes":2,"name":"JSON","percent":0.13,"text":"2 mins","total_seconds":133.793},{"digital":"0:00","hours":0,"minutes":0,"name":"HTML","percent":0.02,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"JavaScript","percent":0.01,"text":"0 secs","total_seconds":7.75},{"digital":"0:00","hours":0,"minutes":0,"name":"Git Config","percent":0,"text":"0 secs","total_seconds":1.041}],"machines":[{"digital":"28:50","hours":28,"machine":{"created_at":"2019-09-25T01:49:30Z","id":"6b19d6df-5ca6-4fc6-87a4-ed72a5252bb6","ip":"115.204.196.113","last_seen_at":"2019-09-27T10:02:03Z","name":"zhulei","value":"zhulei"},"minutes":50,"name":"zhulei","percent":97.67,"text":"28 hrs 50 mins","total_seconds":103859.473},{"digital":"0:41","hours":0,"machine":{"created_at":"2019-09-21T11:10:35Z","id":"4eba099a-4ef2-4864-8324-0e702e0bda93","ip":"183.156.147.102","last_seen_at":"2019-09-22T07:45:02Z","name":"DESKTOP-VP25IP0","value":"DESKTOP-VP25IP0"},"minutes":41,"name":"DESKTOP-VP25IP0","percent":2.33,"text":"41 mins","total_seconds":2475.97}],"modified_at":"2019-09-29T19:07:28Z","operating_systems":[{"digital":"29:32","hours":29,"minutes":32,"name":"Windows","percent":100,"text":"29 hrs 32 mins","total_seconds":106335.443}],"project":null,"projects":[{"digital":"17:20","hours":17,"minutes":20,"name":"senbo","percent":58.73,"text":"17 hrs 20 mins","total_seconds":62454.692},{"digital":"4:56","hours":4,"minutes":56,"name":"test","percent":16.71,"text":"4 hrs 56 mins","total_seconds":17771.137},{"digital":"3:12","hours":3,"minutes":12,"name":"gw","percent":10.88,"text":"3 hrs 12 mins","total_seconds":11564.953},{"digital":"2:41","hours":2,"minutes":41,"name":"jenkins-spring-boot","percent":9.14,"text":"2 hrs 41 mins","total_seconds":9714.270452},{"digital":"0:22","hours":0,"minutes":22,"name":"happy","percent":1.28,"text":"22 mins","total_seconds":1360.615},{"digital":"0:21","hours":0,"minutes":21,"name":"wakatime-sync-master","percent":1.23,"text":"21 mins","total_seconds":1305.343548},{"digital":"0:16","hours":0,"minutes":16,"name":"maven-scala","percent":0.93,"text":"16 mins","total_seconds":986.594},{"digital":"0:16","hours":0,"minutes":16,"name":"Unknown Project","percent":0.93,"text":"16 mins","total_seconds":984.316},{"digital":"0:02","hours":0,"minutes":2,"name":"javaBook-src","percent":0.12,"text":"2 mins","total_seconds":128.761},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-admin","percent":0.05,"text":"0 secs","total_seconds":55.448},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-core","percent":0.01,"text":"0 secs","total_seconds":7.887},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-rest","percent":0,"text":"0 secs","total_seconds":1.189},{"digital":"0:00","hours":0,"minutes":0,"name":"shands-core","percent":0,"text":"0 secs","total_seconds":0.237}],"range":"last_7_days","start":"2019-09-22T16:00:00Z","status":"ok","timeout":130,"timezone":"Asia/Shanghai","total_seconds":102854.661,"total_seconds_including_other_language":106335.443,"user_id":"407ee380-5636-47db-be65-de78a5d12bbd","username":null,"writes_only":false}
     */

    private DataBean data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * best_day : {"created_at":"2019-09-24T02:48:12Z","date":"2019-09-29","id":"0a5c14d0-ad1f-4511-871a-7c5758f48f58","modified_at":"2019-09-29T19:07:28Z","text":"9 hrs 50 mins","total_seconds":35420.84}
         * categories : [{"digital":"29:32","hours":29,"minutes":32,"name":"Coding","percent":100,"text":"29 hrs 32 mins","total_seconds":106335.443}]
         * created_at : 2019-09-24T02:38:28Z
         * daily_average : 20571
         * daily_average_including_other_language : 21267
         * days_including_holidays : 7
         * days_minus_holidays : 5
         * dependencies : [{"digital":"9:20","hours":9,"minutes":20,"name":"springframework.web","percent":10.96,"text":"9 hrs 20 mins","total_seconds":33616.164219},{"digital":"8:09","hours":8,"minutes":9,"name":"shands.core","percent":9.58,"text":"8 hrs 9 mins","total_seconds":29376.323},{"digital":"7:57","hours":7,"minutes":57,"name":"springframework.stereotype","percent":9.34,"text":"7 hrs 57 mins","total_seconds":28653.296968},{"digital":"6:56","hours":6,"minutes":56,"name":"shands.senbo","percent":8.15,"text":"6 hrs 56 mins","total_seconds":24996.167},{"digital":"6:27","hours":6,"minutes":27,"name":"springframework.beans","percent":7.58,"text":"6 hrs 27 mins","total_seconds":23255.110717},{"digital":"5:47","hours":5,"minutes":47,"name":"slf4j.Logger","percent":6.79,"text":"5 hrs 47 mins","total_seconds":20830.83},{"digital":"5:46","hours":5,"minutes":46,"name":"slf4j.LoggerFactory","percent":6.78,"text":"5 hrs 46 mins","total_seconds":20774.378},{"digital":"5:28","hours":5,"minutes":28,"name":"baomidou.mybatisplus","percent":6.43,"text":"5 hrs 28 mins","total_seconds":19725.119},{"digital":"2:59","hours":2,"minutes":59,"name":"lombok.extern","percent":3.52,"text":"2 hrs 59 mins","total_seconds":10789.623486},{"digital":"2:32","hours":2,"minutes":32,"name":"springframework.util","percent":2.98,"text":"2 hrs 32 mins","total_seconds":9141.571232},{"digital":"2:31","hours":2,"minutes":31,"name":"springframework.http","percent":2.96,"text":"2 hrs 31 mins","total_seconds":9078.305398},{"digital":"2:26","hours":2,"minutes":26,"name":"sun.mail","percent":2.87,"text":"2 hrs 26 mins","total_seconds":8802.83},{"digital":"2:24","hours":2,"minutes":24,"name":"springframework.cloud","percent":2.83,"text":"2 hrs 24 mins","total_seconds":8662.6},{"digital":"2:07","hours":2,"minutes":7,"name":"springframework.boot","percent":2.49,"text":"2 hrs 7 mins","total_seconds":7638.656292},{"digital":"1:38","hours":1,"minutes":38,"name":"github.wxpay","percent":1.93,"text":"1 hr 38 mins","total_seconds":5929.278},{"digital":"1:27","hours":1,"minutes":27,"name":"springframework.scheduling","percent":1.7,"text":"1 hr 27 mins","total_seconds":5220.355806},{"digital":"1:23","hours":1,"minutes":23,"name":"junit.Test","percent":1.64,"text":"1 hr 23 mins","total_seconds":5024.66381},{"digital":"1:08","hours":1,"minutes":8,"name":"shands.gw","percent":1.35,"text":"1 hr 8 mins","total_seconds":4128.925},{"digital":"1:07","hours":1,"minutes":7,"name":"huifer.jenkinsspringboot","percent":1.32,"text":"1 hr 7 mins","total_seconds":4045.958398},{"digital":"0:55","hours":0,"minutes":55,"name":"alibaba.fastjson","percent":1.09,"text":"55 mins","total_seconds":3356.424859},{"digital":"0:51","hours":0,"minutes":51,"name":"junit.runner","percent":1.01,"text":"51 mins","total_seconds":3109.17181},{"digital":"0:49","hours":0,"minutes":49,"name":"springframework.test","percent":0.97,"text":"49 mins","total_seconds":2970.25581},{"digital":"0:31","hours":0,"minutes":31,"name":"springframework.data","percent":0.61,"text":"31 mins","total_seconds":1878.91},{"digital":"0:24","hours":0,"minutes":24,"name":"junit.Assert","percent":0.48,"text":"24 mins","total_seconds":1486.507},{"digital":"0:22","hours":0,"minutes":22,"name":"springframework.context","percent":0.45,"text":"22 mins","total_seconds":1375.621672},{"digital":"0:21","hours":0,"minutes":21,"name":"huifer.happy","percent":0.42,"text":"21 mins","total_seconds":1280.456},{"digital":"0:20","hours":0,"minutes":20,"name":"springframework.transaction","percent":0.39,"text":"20 mins","total_seconds":1211.066936},{"digital":"0:17","hours":0,"minutes":17,"name":"springframework.format","percent":0.35,"text":"17 mins","total_seconds":1071.699},{"digital":"0:15","hours":0,"minutes":15,"name":"java.beans.BeanProperty","percent":0.31,"text":"15 mins","total_seconds":953.892},{"digital":"0:13","hours":0,"minutes":13,"name":"wf2311.wakatime","percent":0.26,"text":"13 mins","total_seconds":812.345502},{"digital":"0:13","hours":0,"minutes":13,"name":"apache.ibatis","percent":0.26,"text":"13 mins","total_seconds":787.87},{"digital":"0:11","hours":0,"minutes":11,"name":"google.zxing","percent":0.22,"text":"11 mins","total_seconds":682.355},{"digital":"0:11","hours":0,"minutes":11,"name":"sf.json","percent":0.22,"text":"11 mins","total_seconds":682.355},{"digital":"0:10","hours":0,"minutes":10,"name":"springframework.validation","percent":0.2,"text":"10 mins","total_seconds":618.07},{"digital":"0:09","hours":0,"minutes":9,"name":"cn.hutool","percent":0.19,"text":"9 mins","total_seconds":579.8},{"digital":"0:07","hours":0,"minutes":7,"name":"sun.org","percent":0.15,"text":"7 mins","total_seconds":470.114},{"digital":"0:05","hours":0,"minutes":5,"name":"jboss.shrinkwrap","percent":0.11,"text":"5 mins","total_seconds":341.76},{"digital":"0:05","hours":0,"minutes":5,"name":"jboss.arquillian","percent":0.11,"text":"5 mins","total_seconds":341.76},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Data","percent":0.1,"text":"4 mins","total_seconds":292.54751},{"digital":"0:04","hours":0,"minutes":4,"name":"apache.commons","percent":0.09,"text":"4 mins","total_seconds":289.19818},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.ToString","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Setter","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:04","hours":0,"minutes":4,"name":"lombok.Getter","percent":0.09,"text":"4 mins","total_seconds":284.739},{"digital":"0:03","hours":0,"minutes":3,"name":"apache.http","percent":0.07,"text":"3 mins","total_seconds":216.637},{"digital":"0:03","hours":0,"minutes":3,"name":"security.krb5","percent":0.06,"text":"3 mins","total_seconds":190.52},{"digital":"0:03","hours":0,"minutes":3,"name":"ch.qos","percent":0.06,"text":"3 mins","total_seconds":187.13},{"digital":"0:02","hours":0,"minutes":2,"name":"aspectj.lang","percent":0.06,"text":"2 mins","total_seconds":173.642},{"digital":"0:01","hours":0,"minutes":1,"name":"springframework.jdbc","percent":0.04,"text":"1 min","total_seconds":115.949902},{"digital":"0:01","hours":0,"minutes":1,"name":"springframework.aop","percent":0.04,"text":"1 min","total_seconds":109.218},{"digital":"0:01","hours":0,"minutes":1,"name":"fasterxml.jackson","percent":0.03,"text":"1 min","total_seconds":106.683325},{"digital":"0:01","hours":0,"minutes":1,"name":"shands.gateway","percent":0.03,"text":"1 min","total_seconds":76.918},{"digital":"0:01","hours":0,"minutes":1,"name":"thoughtworks.xstream","percent":0.02,"text":"1 min","total_seconds":62.638},{"digital":"0:01","hours":0,"minutes":1,"name":"bocpay.BocUtil","percent":0.02,"text":"1 min","total_seconds":62.638},{"digital":"0:00","hours":0,"minutes":0,"name":"jodd.http","percent":0.01,"text":"0 secs","total_seconds":33.643074},{"digital":"0:00","hours":0,"minutes":0,"name":"\"./js/lib.336055b3.js\"","percent":0.01,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"\"./js/app.c881f418.js\"","percent":0.01,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"netflix.zuul","percent":0.01,"text":"0 secs","total_seconds":22.253},{"digital":"0:00","hours":0,"minutes":0,"name":"lombok","percent":0,"text":"0 secs","total_seconds":14.579},{"digital":"0:00","hours":0,"minutes":0,"name":"io.jsonwebtoken","percent":0,"text":"0 secs","total_seconds":11.564},{"digital":"0:00","hours":0,"minutes":0,"name":"io.lettuce","percent":0,"text":"0 secs","total_seconds":11.452},{"digital":"0:00","hours":0,"minutes":0,"name":"redis.clients","percent":0,"text":"0 secs","total_seconds":11.452},{"digital":"0:00","hours":0,"minutes":0,"name":"example.demo","percent":0,"text":"0 secs","total_seconds":10.471},{"digital":"0:00","hours":0,"minutes":0,"name":"springframework.amqp","percent":0,"text":"0 secs","total_seconds":8.571},{"digital":"0:00","hours":0,"minutes":0,"name":"wf2311.jfeng","percent":0,"text":"0 secs","total_seconds":6.731902},{"digital":"0:00","hours":0,"minutes":0,"name":"springframework.core","percent":0,"text":"0 secs","total_seconds":5.755}]
         * editors : [{"digital":"29:10","hours":29,"minutes":10,"name":"IntelliJ","percent":98.77,"text":"29 hrs 10 mins","total_seconds":105030.099452},{"digital":"0:21","hours":0,"minutes":21,"name":"VS Code","percent":1.23,"text":"21 mins","total_seconds":1305.343548}]
         * end : 2019-09-29T15:59:59Z
         * holidays : 2
         * human_readable_daily_average : 5 hrs 42 mins
         * human_readable_daily_average_including_other_language : 5 hrs 54 mins
         * human_readable_total : 28 hrs 34 mins
         * human_readable_total_including_other_language : 29 hrs 32 mins
         * id : 103ff3cb-d8df-4af4-991d-5022d50c9c36
         * is_already_updating : false
         * is_coding_activity_visible : false
         * is_other_usage_visible : false
         * is_stuck : false
         * is_up_to_date : true
         * languages : [{"digital":"24:39","hours":24,"minutes":39,"name":"Java","percent":83.49,"text":"24 hrs 39 mins","total_seconds":88781.850698},{"digital":"1:40","hours":1,"minutes":40,"name":"YAML","percent":5.65,"text":"1 hr 40 mins","total_seconds":6007.970564},{"digital":"0:58","hours":0,"minutes":58,"name":"Other","percent":3.27,"text":"58 mins","total_seconds":3480.782},{"digital":"0:51","hours":0,"minutes":51,"name":"XML","percent":2.89,"text":"51 mins","total_seconds":3076.487},{"digital":"0:32","hours":0,"minutes":32,"name":"SQL","percent":1.86,"text":"32 mins","total_seconds":1976.706},{"digital":"0:16","hours":0,"minutes":16,"name":"Text","percent":0.93,"text":"16 mins","total_seconds":984.803},{"digital":"0:15","hours":0,"minutes":15,"name":"Scala","percent":0.9,"text":"15 mins","total_seconds":953.892},{"digital":"0:09","hours":0,"minutes":9,"name":"Markdown","percent":0.54,"text":"9 mins","total_seconds":569.705738},{"digital":"0:02","hours":0,"minutes":2,"name":"Properties","percent":0.16,"text":"2 mins","total_seconds":174.046},{"digital":"0:02","hours":0,"minutes":2,"name":"Groovy","percent":0.15,"text":"2 mins","total_seconds":162.109},{"digital":"0:02","hours":0,"minutes":2,"name":"JSON","percent":0.13,"text":"2 mins","total_seconds":133.793},{"digital":"0:00","hours":0,"minutes":0,"name":"HTML","percent":0.02,"text":"0 secs","total_seconds":24.507},{"digital":"0:00","hours":0,"minutes":0,"name":"JavaScript","percent":0.01,"text":"0 secs","total_seconds":7.75},{"digital":"0:00","hours":0,"minutes":0,"name":"Git Config","percent":0,"text":"0 secs","total_seconds":1.041}]
         * machines : [{"digital":"28:50","hours":28,"machine":{"created_at":"2019-09-25T01:49:30Z","id":"6b19d6df-5ca6-4fc6-87a4-ed72a5252bb6","ip":"115.204.196.113","last_seen_at":"2019-09-27T10:02:03Z","name":"zhulei","value":"zhulei"},"minutes":50,"name":"zhulei","percent":97.67,"text":"28 hrs 50 mins","total_seconds":103859.473},{"digital":"0:41","hours":0,"machine":{"created_at":"2019-09-21T11:10:35Z","id":"4eba099a-4ef2-4864-8324-0e702e0bda93","ip":"183.156.147.102","last_seen_at":"2019-09-22T07:45:02Z","name":"DESKTOP-VP25IP0","value":"DESKTOP-VP25IP0"},"minutes":41,"name":"DESKTOP-VP25IP0","percent":2.33,"text":"41 mins","total_seconds":2475.97}]
         * modified_at : 2019-09-29T19:07:28Z
         * operating_systems : [{"digital":"29:32","hours":29,"minutes":32,"name":"Windows","percent":100,"text":"29 hrs 32 mins","total_seconds":106335.443}]
         * project : null
         * projects : [{"digital":"17:20","hours":17,"minutes":20,"name":"senbo","percent":58.73,"text":"17 hrs 20 mins","total_seconds":62454.692},{"digital":"4:56","hours":4,"minutes":56,"name":"test","percent":16.71,"text":"4 hrs 56 mins","total_seconds":17771.137},{"digital":"3:12","hours":3,"minutes":12,"name":"gw","percent":10.88,"text":"3 hrs 12 mins","total_seconds":11564.953},{"digital":"2:41","hours":2,"minutes":41,"name":"jenkins-spring-boot","percent":9.14,"text":"2 hrs 41 mins","total_seconds":9714.270452},{"digital":"0:22","hours":0,"minutes":22,"name":"happy","percent":1.28,"text":"22 mins","total_seconds":1360.615},{"digital":"0:21","hours":0,"minutes":21,"name":"wakatime-sync-master","percent":1.23,"text":"21 mins","total_seconds":1305.343548},{"digital":"0:16","hours":0,"minutes":16,"name":"maven-scala","percent":0.93,"text":"16 mins","total_seconds":986.594},{"digital":"0:16","hours":0,"minutes":16,"name":"Unknown Project","percent":0.93,"text":"16 mins","total_seconds":984.316},{"digital":"0:02","hours":0,"minutes":2,"name":"javaBook-src","percent":0.12,"text":"2 mins","total_seconds":128.761},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-admin","percent":0.05,"text":"0 secs","total_seconds":55.448},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-core","percent":0.01,"text":"0 secs","total_seconds":7.887},{"digital":"0:00","hours":0,"minutes":0,"name":"gw-rest","percent":0,"text":"0 secs","total_seconds":1.189},{"digital":"0:00","hours":0,"minutes":0,"name":"shands-core","percent":0,"text":"0 secs","total_seconds":0.237}]
         * range : last_7_days
         * start : 2019-09-22T16:00:00Z
         * status : ok
         * timeout : 130
         * timezone : Asia/Shanghai
         * total_seconds : 102854.661
         * total_seconds_including_other_language : 106335.443
         * user_id : 407ee380-5636-47db-be65-de78a5d12bbd
         * username : null
         * writes_only : false
         */

        private BestDayBean bestDay;
        private String createdAt;
        private int dailyAverage;
        private int dailyAverageIncludingOtherLanguage;
        private int daysIncludingHolidays;
        private int daysMinusHolidays;
        private String end;
        private int holidays;
        private String humanReadableDailyAverage;
        private String humanReadableDailyAverageIncludingOtherLanguage;
        private String humanReadableTotal;
        private String humanReadableTotalIncludingOtherLanguage;
        private String id;
        private boolean isAlreadyUpdating;
        private boolean isCodingActivityVisible;
        private boolean isOtherUsageVisible;
        private boolean isStuck;
        private boolean isUpToDate;
        private String modifiedAt;
        private Object project;
        private String range;
        private String start;
        private String status;
        private int timeout;
        private String timezone;
        private double totalSeconds;
        private double totalSecondsIncludingOtherLanguage;
        private String userId;
        private Object username;
        private boolean writesOnly;
        private List<CategoriesBean> categories;
        private List<DependenciesBean> dependencies;
        private List<EditorsBean> editors;
        private List<LanguagesBean> languages;
        private List<MachinesBean> machines;
        private List<OperatingSystemsBean> operatingSystems;
        private List<ProjectsBean> projects;

        @NoArgsConstructor
        @Data
        public static class BestDayBean {
            /**
             * created_at : 2019-09-24T02:48:12Z
             * date : 2019-09-29
             * id : 0a5c14d0-ad1f-4511-871a-7c5758f48f58
             * modified_at : 2019-09-29T19:07:28Z
             * text : 9 hrs 50 mins
             * total_seconds : 35420.84
             */

            private String createdAt;
            private String date;
            private String id;
            private String modifiedAt;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class CategoriesBean {
            /**
             * digital : 29:32
             * hours : 29
             * minutes : 32
             * name : Coding
             * percent : 100
             * text : 29 hrs 32 mins
             * total_seconds : 106335.443
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private int percent;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class DependenciesBean {
            /**
             * digital : 9:20
             * hours : 9
             * minutes : 20
             * name : springframework.web
             * percent : 10.96
             * text : 9 hrs 20 mins
             * total_seconds : 33616.164219
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private double percent;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class EditorsBean {
            /**
             * digital : 29:10
             * hours : 29
             * minutes : 10
             * name : IntelliJ
             * percent : 98.77
             * text : 29 hrs 10 mins
             * total_seconds : 105030.099452
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private double percent;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class LanguagesBean {
            /**
             * digital : 24:39
             * hours : 24
             * minutes : 39
             * name : Java
             * percent : 83.49
             * text : 24 hrs 39 mins
             * total_seconds : 88781.850698
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private double percent;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class MachinesBean {
            /**
             * digital : 28:50
             * hours : 28
             * machine : {"created_at":"2019-09-25T01:49:30Z","id":"6b19d6df-5ca6-4fc6-87a4-ed72a5252bb6","ip":"115.204.196.113","last_seen_at":"2019-09-27T10:02:03Z","name":"zhulei","value":"zhulei"}
             * minutes : 50
             * name : zhulei
             * percent : 97.67
             * text : 28 hrs 50 mins
             * total_seconds : 103859.473
             */

            private String digital;
            private int hours;
            private MachineBean machine;
            private int minutes;
            private String name;
            private double percent;
            private String text;
            private double totalSeconds;

            @NoArgsConstructor
            @Data
            public static class MachineBean {
                /**
                 * created_at : 2019-09-25T01:49:30Z
                 * id : 6b19d6df-5ca6-4fc6-87a4-ed72a5252bb6
                 * ip : 115.204.196.113
                 * last_seen_at : 2019-09-27T10:02:03Z
                 * name : zhulei
                 * value : zhulei
                 */

                private String createdAt;
                private String id;
                private String ip;
                private String lastSeenAt;
                private String name;
                private String value;
            }
        }

        @NoArgsConstructor
        @Data
        public static class OperatingSystemsBean {
            /**
             * digital : 29:32
             * hours : 29
             * minutes : 32
             * name : Windows
             * percent : 100
             * text : 29 hrs 32 mins
             * total_seconds : 106335.443
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private int percent;
            private String text;
            private double totalSeconds;
        }

        @NoArgsConstructor
        @Data
        public static class ProjectsBean {
            /**
             * digital : 17:20
             * hours : 17
             * minutes : 20
             * name : senbo
             * percent : 58.73
             * text : 17 hrs 20 mins
             * total_seconds : 62454.692
             */

            private String digital;
            private int hours;
            private int minutes;
            private String name;
            private double percent;
            private String text;
            private double totalSeconds;
        }
    }
}
