package com.huifer.jenkinsspringboot.util;

import com.huifer.jenkinsspringboot.config.SettingVar;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 日期工具
 *
 * @author huifer
 * @date 2019-09-30
 */
@Slf4j
public class DateUtils {


    private DateUtils() {
        throw new RuntimeException("this is a utils");
    }

    public static void main(String[] args) {
        getStart2End();
    }

    public static List<String> getStart2End() {
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String dateStart = SettingVar.START_TIME;
//            String dateEnd = date.format(new Date());
            String dateEnd = SettingVar.END_TIME;
            long startTime = date.parse(dateStart).getTime();
            long endTime = date.parse(dateEnd).getTime();
            long day = 1000 * 60 * 60 * 24;
            List<String> res = new ArrayList<>();

            for (long i = startTime; i <= endTime; i += day) {
                String format = date.format(new Date(i));
                res.add(format);
            }
            return res;
        } catch (ParseException ex) {
            log.error("日期格式化失败");
        }
        return null;
    }


    public static long day2timestamp(String day) {
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(day);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            log.error("日期格式化失败");
        }
        return 0;
    }

    /**
     * 获取昨天的日期
     *
     * @return 昨日年月日
     */
    public static String getYestday() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String yest = df.format(date);
        return yest;
    }

    /**
     * 返回事时间描述
     *
     * @param hours
     * @param minutes
     * @return xxx小时 xxx分钟
     */
    public static String timeText(int hours, int minutes) {
        return String.format("%d 小时 %d分钟", hours, minutes);
    }
}
