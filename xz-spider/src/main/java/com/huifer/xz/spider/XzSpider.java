package com.huifer.xz.spider;

import com.alibaba.fastjson.JSONObject;
import com.huifer.xz.entity.*;
import com.huifer.xz.mq.MsgProducer;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.huifer.xz.util.TestXml2Json.dom4j2Json;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@Slf4j
@Service
public class XzSpider {
    public static final int MAX_CITY_ID = 414;
    public static final String BASE_XZ_URL = "http://www.imxingzhe.com/";
    public static final int START_YEAR = 2010;
    public static final int END_YEAR = 2019;
    public static final int MONTH = 12;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MsgProducer rabbitSender;

    /**
     * 正则匹配用
     *
     * @param str
     * @param regex
     * @return
     */
    private static String regex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);

        while (match.find()) {
            if (!"".equals(match.group())) {
                String group = match.group();
                return group;
            }
        }
        return "";
    }


    /**
     * 获取用户历史记录
     * "http://www.imxingzhe.com/api/v4/user_month_info?user_id=%d&year=%d&month=%d"
     *
     * @param url
     */
    public void spiderUserHistory(TXz user) {
        LocalDate localDate = LocalDate.now();

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        for (int y = START_YEAR; y <= END_YEAR; y++) {
            for (int m = 1; m <= MONTH; m++) {
                if (y == year && m > month) {
                    break;
                } else {
                    getUserMonthInfo(user, y, m);
                }
            }
        }
    }

    /**
     * 获取用户年月数据
     *
     * @param user
     * @param y
     * @param m
     */
    private void getUserMonthInfo(TXz user, int y, int m) {
        String url = String.format("http://www.imxingzhe.com/api/v4/user_month_info?user_id=%d&year=%d&month=%d", user.getUserId(), y, m);
        ResponseEntity<String> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(headers()),

                String.class
        );
        String body = exchange.getBody();
        JSONObject object = JSONObject.parseObject(body);
        UserMonthInfo userMonthInfo = object.toJavaObject(UserMonthInfo.class);
        userMonthInfo.setMonth(m);
        userMonthInfo.setYear(y);
        userMonthInfo.setUserName(user.getName());
        userMonthInfo.setUserUrl(user.getUrl());

        UserMonthInfo.DataBean.StInfoBean stInfo = userMonthInfo.getData().getStInfo();
        List<UserMonthInfo.DataBean.WoInfoBean> woInfo = userMonthInfo.getData().getWoInfo();


        List<String> range = this.stringRedisTemplate.opsForList().range(RedisKey.XZ_USER_MONTH, 0, -1);
        List<UserMonthInfo> userMonthInfos = this.listStringToObj(range, UserMonthInfo.class);
        if (!userMonthInfos.contains(userMonthInfo)) {
            this.stringRedisTemplate.opsForList().rightPush(RedisKey.XZ_USER_MONTH, JSONObject.toJSONString(userMonthInfo));
            insertSt(stInfo, y, m, user.getUserId());
            insertWo(woInfo, y, m);
        } else {
            log.error("当前用户月信息已存在={}", userMonthInfo);
        }
    }

    /**
     * st 数据
     * // TODO: 2019/10/17 后续写入数据库
     *
     * @param st
     * @param year
     * @param month
     * @param userId
     */
    private void insertSt(UserMonthInfo.DataBean.StInfoBean st, int year, int month, int userId) {
        TStinfo tStinfo = new TStinfo();
        tStinfo.setYear(year);
        tStinfo.setMonth(month);

        tStinfo.setSumDuration(st.getSumDuration());
        tStinfo.setSumDistance(st.getSumDistance());
        tStinfo.setSumElevationGain(st.getSumElevationGain());
        tStinfo.setCountDistance(st.getCountDistance());
        tStinfo.setSumCredits(st.getSumCredits());
        tStinfo.setUserId(userId);
    }

    /**
     * wo数据
     * // TODO: 2019/10/17 后续写入数据库
     *
     * @param wo
     * @param year
     * @param month
     */
    private void insertWo(List<UserMonthInfo.DataBean.WoInfoBean> wo, int year, int month) {
        for (UserMonthInfo.DataBean.WoInfoBean woInfoBean : wo) {
            TWoinfo tWoinfo = new TWoinfo();
            tWoinfo.setYear(year);
            tWoinfo.setMonth(month);
            tWoinfo.setHeartSource(woInfoBean.getHeartSource());
            tWoinfo.setLikeCount(woInfoBean.getLikeCount());
            tWoinfo.setDuration(woInfoBean.getDuration());
            tWoinfo.setSport(woInfoBean.getSport());
            tWoinfo.setId(woInfoBean.getId());
            tWoinfo.setUploadTime(woInfoBean.getUploadTime());
            tWoinfo.setUserId(woInfoBean.getUserId());
            tWoinfo.setUuid(woInfoBean.getUuid());
            tWoinfo.setTitle(woInfoBean.getTitle());
            tWoinfo.setCadenceSource(woInfoBean.getCadenceSource());
            tWoinfo.setIsValid(woInfoBean.getIsValid());
            tWoinfo.setCommentCount(woInfoBean.getCommentCount());
            tWoinfo.setElevationLoss(woInfoBean.getElevationLoss());
            tWoinfo.setHidden(woInfoBean.getHidden() == Boolean.TRUE ? BigDecimal.ONE : BigDecimal.ZERO);
            tWoinfo.setDesc(woInfoBean.getDesc());
            tWoinfo.setThreedWorkout(woInfoBean.getThreedWorkout());
            tWoinfo.setMapId(woInfoBean.getMapId());
            tWoinfo.setElevationGain(woInfoBean.getElevationGain());
            tWoinfo.setStartTime(woInfoBean.getStartTime());
            tWoinfo.setCredits(woInfoBean.getCredits());
            tWoinfo.setIsSegment(woInfoBean.getIsSegment());
            tWoinfo.setIsLike(woInfoBean.getIsLike());
            tWoinfo.setDistance(woInfoBean.getDistance());
            tWoinfo.setCalories(woInfoBean.getCalories());
            tWoinfo.setLocSource(woInfoBean.getLocSource());
            tWoinfo.setMapHidden(woInfoBean.getMapHidden());
            tWoinfo.setEndTime(woInfoBean.getEndTime());
            tWoinfo.setAvgSpeed(woInfoBean.getAvgSpeed());

            // TODO: 2019/10/17 twoinfo 有路线的id 把整个发给消息队列
            this.rabbitSender.sendWo(JSONObject.toJSONString(tWoinfo));
        }
    }

    /**
     * 获取城市第n页信息
     * http://www.imxingzhe.com/city/%d/?page=%d
     *
     * @param cityUrlInfo
     */
    public String spiderCityPage(CityUrlInfo cityUrlInfo) {
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    cityUrlInfo.getUrl(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headers()),
                    String.class
            );
            String body = exchange.getBody();
            Document doc = Jsoup.parse(body);
            Elements rows = doc.select("a[class=user-name]");
            for (Element e : rows) {
                String name = e.text();
                String userUrl = BASE_XZ_URL + e.attr("href");
                TXz userPro = new TXz();
                userPro.setCityId(cityUrlInfo.getCityId());
                userPro.setName(name);
                userPro.setUrl(userUrl);
                userPro.setPage(cityUrlInfo.getPage());
                userPro.setUserId(url2userId(userUrl));
                //更新redis 1. 删除list中的中的数据2. 重新写回数据

                // TODO: 2019/10/17 TXz 需要写入数据库
                log.info("当前城市信息数据获取成功,写入redis，key={},value={}", RedisKey.XZ_USER_URL, userPro);
                // 设置成功url
                stringRedisTemplate.opsForList().rightPush(RedisKey.CITY_URL_LIST_SUCCESS, JSONObject.toJSONString(cityUrlInfo));
                stringRedisTemplate.opsForList().rightPush(RedisKey.XZ_USER_URL, JSONObject.toJSONString(userPro));
                // 发送数据到mq
                this.rabbitSender.sendUserInfo(JSONObject.toJSONString(userPro));
            }

            return "用户数据获取成功";
        } catch (Exception e) {
            log.error("当前城市信息数据获取失败,写入redis，key={},value={}", RedisKey.CITY_URL_LIST_ERROR, cityUrlInfo);
            stringRedisTemplate.opsForList().rightPush(RedisKey.CITY_URL_LIST_ERROR, JSONObject.toJSONString(cityUrlInfo));
            return "用户数据获取失败";
        }

    }

    /**
     * 用户url 转换成用户uid
     *
     * @param url
     * @return
     */
    private Integer url2userId(String url) {
        ResponseEntity<String> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(headers()),
                String.class
        );
        String body = exchange.getBody();
        String regex = "uid.*\\d+.*";
        String regex1 = regex(body, regex);
        String uidStr = regex(regex1, "\\d+");
        Integer uid = Integer.valueOf(uidStr);
        log.info("当前url={},对应uid={}", url, uid);
        return uid;
    }

    /**
     * 获取城市列表信息爬虫入口
     */
    public void cityInfo() {
        SetOperations<String, CityInfo> stringCityInfoSetOperations = new RedisTemplate<String, CityInfo>().opsForSet();
        for (int i = 1; i <= MAX_CITY_ID; i++) {
            CityInfo cityInfo = getCityInfo(i);
            Set<String> members = stringRedisTemplate.opsForSet().members(RedisKey.CITY_KEY);
            Set<CityInfo> cityInfos = setStringToObj(members, CityInfo.class);
            // 确认是否存在这个城市的信息
            if (!cityInfos.contains(cityInfo)) {
                stringRedisTemplate.opsForSet().add(RedisKey.CITY_KEY, JSONObject.toJSONString(cityInfo));
                setCityUrl(cityInfo, 1);
            } else {
                List<String> members1 = stringRedisTemplate.opsForList().range(RedisKey.CITY_URL_LIST + cityInfo.getCityId() + ":", 0, -1);
                setCityUrl(cityInfo, members1.size() + 1);
            }
        }
    }

    /**
     * 设置城市url列表
     *
     * @param cityInfo
     * @param start
     */
    private void setCityUrl(CityInfo cityInfo, int start) {
        for (int i = start; i <= cityInfo.getPageSize(); i++) {
            String url = String.format("http://www.imxingzhe.com/city/%d/?page=%d", cityInfo.getCityId(), i);
            String rk = RedisKey.CITY_URL_LIST + cityInfo.getCityId() + ":";
            CityUrlInfo cityUrlInfo = new CityUrlInfo();
            cityUrlInfo.setCName(cityInfo.getCityName());
            cityUrlInfo.setUrl(url);
            cityUrlInfo.setIsWork(false);
            cityUrlInfo.setCityId(cityInfo.getCityId());
            cityUrlInfo.setPage(i);
            cityUrlInfo.setRedisKey(rk);

            List<String> range = stringRedisTemplate.opsForList().range(rk, 0, -1);
            List<CityUrlInfo> cityUrlInfos = this.listStringToObj(range, CityUrlInfo.class);
            if (!cityUrlInfos.contains(cityUrlInfo)) {
                stringRedisTemplate.opsForList().rightPush(rk, JSONObject.toJSONString(cityUrlInfo));
                rabbitSender.sendCityUrl(JSONObject.toJSONString(cityUrlInfo));
            } else {
                log.error("当前城市列表已存在={}", cityUrlInfo);
            }
        }
    }

    /**
     * 还原实体类
     *
     * @param members
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> Set<T> setStringToObj(Set<String> members, Class<T> clazz) {
        Set<T> s = new HashSet<>();
        for (String member : members) {

            JSONObject object = JSONObject.parseObject(member);
            T t = object.toJavaObject(clazz);
            s.add(t);
        }
        return s;
    }

    private <T> List<T> listStringToObj(List<String> members, Class<T> clazz) {
        List<T> s = new ArrayList<>();
        for (String member : members) {

            JSONObject object = JSONObject.parseObject(member);
            T t = object.toJavaObject(clazz);
            s.add(t);
        }
        return s;
    }

    /**
     * 获取一个城市的数据
     *
     * @param cityId
     * @return
     */
    private CityInfo getCityInfo(int cityId) {
        String url = String.format("http://www.imxingzhe.com/city/%d/?page=1", cityId);
        try {

            ResponseEntity<String> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers()),
                    String.class
            );

            String body = exchange.getBody();
            Document doc = Jsoup.parse(body);
            Elements rows = doc.select("div[class=col-md-6 alist active]");
            String cityName = rows.text().replace("用户排名", "");

            String regex = "\\d+.*//总页码";
            String regex1 = regex(body, regex);
            int total = Integer.parseInt(regex(regex1, "\\d+"));
            CityInfo cityInfo = new CityInfo();
            cityInfo.setCityId(cityId);
            cityInfo.setCityName(cityName);
            cityInfo.setPageSize(total);
            // TODO: 2019/10/17 城市信息写入数据库
            return cityInfo;
        } catch (Exception e) {
            log.error("当前url访问失败={}", url);
            stringRedisTemplate.opsForSet().add(RedisKey.CITY_INFO_ERROR_URL, url);
            return null;
        }
    }

    /**
     * 请求头信息
     *
     * @return
     */
    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        headers.add("Cookie", "td_cookie=2522742900; td_cookie=2441244528; csrftoken=nQKAt5cwYT9dsIjBteRKSaNLQZnZynZ3; sessionid=5lx3yvdfwsacv0eaif7rfy6wrvy1x62h; Hm_lvt_7b262f3838ed313bc65b9ec6316c79c4=1571183765,1571185968,1571189278,1571288811; Hm_lpvt_7b262f3838ed313bc65b9ec6316c79c4=1571288811");
        headers.add("Host", "www.imxingzhe.com");
        headers.add("Upgrade-Insecure-Requests", "1");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
        return headers;
    }

    /**
     * 用户路线爬虫
     * http://www.imxingzhe.com/xing/70100820/gpx/
     *
     * @param tWoinfo
     */
    public void spiderLine(TWoinfo tWoinfo) {
        String url = String.format("http://www.imxingzhe.com/xing/%d/gpx/", Integer.parseInt(tWoinfo.getId().toString()));
        try {

            ResponseEntity<String> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers()),
                    String.class
            );
            String body = exchange.getBody();
            org.dom4j.Document doc = null;
            doc = DocumentHelper.parseText(body);
            JSONObject json = new JSONObject();
            dom4j2Json(doc.getRootElement(), json);
            String replace = json.toJSONString().replace("@", "");
            Linex linex = json.toJavaObject(Linex.class);

            List<String> range = this.stringRedisTemplate.opsForList().range(RedisKey.XZ_LINE, 0, -1);
            List<Linex> linexes = this.listStringToObj(range, Linex.class);

            if (!linexes.contains(linex)) {
                this.stringRedisTemplate.opsForList().rightPush(RedisKey.XZ_LINE, JSONObject.toJSONString(linex));
            } else {
                log.error("当前路线已存在={}", tWoinfo.getId());
            }

        } catch (Exception e) {
            log.error("当前url不能获取路线数据={}", url);
            this.stringRedisTemplate.opsForList().rightPush(RedisKey.XZ_LINE_ERROR, JSONObject.toJSONString(url));
        }
    }
}
