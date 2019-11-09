package com.huifer.utils.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateHelper {
    public final static SimpleDateFormat g_SimpleDateFormat = new SimpleDateFormat(
            "yyyyMMdd");
    public final static SimpleDateFormat g_SimpleDateFormat_I = new SimpleDateFormat(
            "yyyy-MM-dd");
    public final static SimpleDateFormat g_SimpleDateFormat_II = new SimpleDateFormat(
            "yyyyMM");
    public final static SimpleDateFormat sdfDateTimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_I = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public final static SimpleDateFormat sdfDateTimeFormat_IIII = new SimpleDateFormat(
            "HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_YYYY = new SimpleDateFormat(
            "yyyy");
    public final static SimpleDateFormat x_dateTimeFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");

    public final static SimpleDateFormat xISO_dateTimeFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss Z");

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 返回当前时间日期格式(yyyyMMddHHmmss)
     *
     * @return
     */
    public static String getCurDateTime() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime(java.util.Date date) {
        return sdfDateTimeFormat.format(date);
    }

    /**
     * 返回日期格式(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String getDateTime2(java.util.Date date) {
        return g_SimpleDateFormat.format(date);
    }

    /**
     * 返回日期格式(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String getDateTime_I(java.util.Date date) {
        return g_SimpleDateFormat_I.format(date);
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_III(java.util.Date date) {
        return sdfDateTimeFormat.format(date);
    }

    /**
返回日期格式(yyyy-MM-dd HH:mm:ss)

     *
     * @return
     */
    public static String getCurrDateTime() {
        return sdfDateTimeFormat.format(new Date());
    }

    /**
     * 返回时间格式(HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_IIII(java.util.Date date) {
        return sdfDateTimeFormat_IIII.format(date);
    }

    /**
     * 返回日期格式(yyyyMMddHHmmss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_IV(java.util.Date date) {
        return sdfDateTimeFormat_I.format(date);
    }

    /**
     * 获取当前日期(yyyyMMdd)
     *
     * @return
     */
    public static String getCurDate() {
        return g_SimpleDateFormat.format(new Date());
    }

    /**
     * 获取当前系统时间的小时数
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到当前时间分钟
     *
     * @return
     */
    public static int getCurrentMinute() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取当前年月格式(yyyyMM)
     *
     * @return
     */
    public static String getCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        return g_SimpleDateFormat_II.format(calendar.getTime());
    }

    public static String getMonth(Date date) {
        return g_SimpleDateFormat_II.format(date);
    }

    /**
     * 获得本月的前（后）几个月。(yyyyMM)
     *
     * @param monthNum 月数字(数字大于1为后，小于1为前)
     * @return
     */
    public static String getBAMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return g_SimpleDateFormat_II.format(calendar.getTime());
    }

    /**
     * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
     *
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1)// 当月第一日
        {
            calendar.add(Calendar.MONTH, -1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10) {
            return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
        } else {
            return "" + calendar.get(Calendar.YEAR) + (month + 1) + "01";
        }
    }

    /**
     * 获取当前时间前几天或后几天的日期.
     * 日数字(数字大于1为后，小于1为前)
     *
     * @return
     */
    public static Date getAddDays(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 获取某个月后的日期格式（yyyyMMdd）
     *
     * @return
     */
    public static String getAfterMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return g_SimpleDateFormat.format(calendar.getTime());
    }

    /**
     * 返回日期（格式yyyyMMdd）
     *
     * @param timeMillis
     * @return
     */
    public static String getFormatDate(long timeMillis) {
        return sdfDateTimeFormat_I.format(new Date(timeMillis));
    }

    /**
     * 获取当前系统时间距离传入时间的毫秒数
     *
     * @param strTime 格式[ DD:00:00]
     * @return
     * @throws ParseException
     */
    public static long getSleepTime(String strTime) throws ParseException {
        long p = 1;
        long l_date = System.currentTimeMillis();
        java.util.Date date_now = new java.util.Date(l_date);
        String strDate = g_SimpleDateFormat_I.format(date_now) + strTime;
        if (date_now.before(sdfDateTimeFormat.parse(strDate)))
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date_now);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            java.util.Date date = calendar.getTime();
            strDate = g_SimpleDateFormat_I.format(date) + strTime;
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        }
        return p;
    }

    /**
     * 返回当前日期的前一天 返回格式（YYYY-mm-dd）
     *
     * @return
     */
    public static String getPredate() {
        Date nowDate = new Date();
        String nowdates = g_SimpleDateFormat_I.format(nowDate);
        String[] dates = nowdates.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]) - 1;
        if (day == 0) {
            switch (month - 1) {
                case 1:
                    day = 31;
                    break;
                case 3:
                    day = 31;
                    break;
                case 5:
                    day = 31;
                    break;
                case 7:
                    day = 31;
                    break;
                case 8:
                    day = 31;
                    break;
                case 10:
                    day = 31;
                    break;
                case 0:
                    month = 13;
                    year = year - 1;
                    day = 31;
                    break;
                case 4:
                    day = 30;
                    break;
                case 6:
                    day = 30;
                    break;
                case 9:
                    day = 30;
                    break;
                case 11:
                    day = 30;
                    break;
                case 2:
                    if (year % 4 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    break;
            }
            month = month - 1;
        }
        String predate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
        return predate;
    }

    /**
     * 得到xxxx年xx月xx日 日期格式。
     *
     * @param date
     * @return
     */
    public static String getChinaDateFormat(Date date) {
        // 得到yyyy-mm-dd格式日期格式
        String dateStr = getDateTime_I(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr != null && dateStr.length() > 0) {
            String[] newStr = dateStr.split("-");
            // 得到月
            Integer month = Integer.valueOf(newStr[1]);
            // 得到日
            Integer day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * 得到xxxx年xx月xx日xx时xx分日期格式。
     *
     * @param date
     * @return
     */
    public static String getChinaDateFormat_II(Date date) {
        // 得到yyyy-mm-dd HH:mm:ss格式日期格式
        String dateStr = getDateTime(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr.length() > 0) {
            String[] str = dateStr.split(" ");
            if (str.length > 1) {
                String[] newStr = str[0].split("-");
                // 得到月
                Integer month = Integer.valueOf(newStr[1]);
                // 得到日
                Integer day = Integer.valueOf(newStr[2]);
                sb.append(newStr[0]).append("年");
                sb.append(month).append("月").append(day).append("日");
                String[] newStr_II = str[1].split(":");
                sb.append(newStr_II[0]).append("时").append(newStr_II[1]).append("分");
            }
        }
        return sb.toString();
    }

    /**
     * 得到xxxx年xx月xx日 日期格式。
     *
     * @param date 格式必须是 2019-01-01字符串
     * @return
     */
    public static String getChinaDateFormat(String date) {
        // 得到yyyy-mm-dd格式日期格式
        StringBuffer sb = new StringBuffer();
        if (date != null && date.length() > 0) {
            String[] newStr = date.split("-");
            // 得到月
            Integer month = Integer.valueOf(newStr[1]);
            // 得到日
            Integer day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * 判断一个日期字符串是否合法
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean isDateStringCorrect(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将字符串类型的日期格式 转换为 符合要求的日期格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStrDate4String(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                Date d = df.parse(date);
                return df.format(d);
            } catch (ParseException e) {
                return "";
            }
        }
    }

    /**
     * 将Date类型的日期格式 转换为 符合要求的 String日期格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStrDate4Date(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }

    /**
     * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate4StrDate(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 计算指定日期时间之间的时间差
     *
     * @param beginStr 开始日期字符串
     * @param endStr   结束日期字符串
     * @param f        时间差的形式0-秒,1-分种,2-小时,3--天 日期时间字符串格式:yyyyMMddHHmmss
     */
    public static int getInterval(String beginStr, String endStr, int f) {
        int hours = 0;
        try {
            Date beginDate = sdfDateTimeFormat.parse(beginStr);
            Date endDate = sdfDateTimeFormat.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hours;
    }

    /**
     * 得到起始日期前或后天数的日期
     *
     * @param starttime 起始日期 格式：yyyy-MM-dd
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date getStartDateInterval(String starttime, int days) {
        Date startDate = null;
        try {
            startDate = g_SimpleDateFormat_I.parse(starttime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(startDate);

        startTime.add(Calendar.DAY_OF_YEAR, days);
        return startTime.getTime();
    }

    /**
     * 得到起始日期和结束日期之间的天数
     *
     * @param beginStr 起始日期
     * @param endStr   结束日期
     * @param format   根据 日期参数的格式，传对应的SimpleDateFormat格式
     * @return 天数
     */
    public static int getDaysInterval(String beginStr, String endStr,
                                      SimpleDateFormat format) {

        try {
            Date beginDate = format.parse(beginStr);
            Date endDate = format.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            return (int) (millisecond / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到某个时间当天的最后时间 （时分秒为 23:59:59形式)
     */
    public static Date getEndDate(Date date) {
        if (date != null) {
            //将截止时间设为指定日期的23:59:59
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
        }
        return date;
    }

    /**
     * 得到本月第一天
     *
     * @return
     */
    public static Date getFristDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }

    /**
     * 得到本月最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }

    /**
     * 获得本周一的日期
     *
     * @return
     * @throws ParseException
     */
    public static Date getMondayOFWeek() throws ParseException {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String dateStr = getDateTime_I(monday);
        StringBuffer sb = new StringBuffer(dateStr);
        sb.append(" ").append("00:00:00");
        return parseDateStrToDate(sb.toString());
    }

    /**
     * 获得本周星期日的日期
     *
     * @return
     * @throws ParseException
     */
    public static Date getCurrentWeekday() throws ParseException {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        String dateStr = getDateTime_I(monday);
        StringBuffer sb = new StringBuffer(dateStr);
        sb.append(" ").append("00:00:00");
        return parseDateStrToDate(sb.toString());
    }

    /**
     * 字符串时间格式转换为 Date
     *
     * @param date 此格式 yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date parseDateStrToDate(String date)
            throws ParseException {
        Date date_time = null;
        if (!StringUtils.isEmpty(date)) {
            date_time = sdfDateTimeFormat.parse(date);
        }
        return date_time;
    }

    /**
     * 字符串时间格式转换为 Date
     *
     * @param date 此格式 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date parseDateFromStr(String date)
            throws ParseException {
        Date date_time = null;
        if (!StringUtils.isEmpty(date)) {
            date_time = g_SimpleDateFormat_I.parse(date);
        }
        return date_time;
    }

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 因为按中国礼拜一作为第一天所以这里减1
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else if (dayOfWeek == 0) {
            return 1 - 7;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 得到当天开始时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getTodayStartDateTime(Date date) throws ParseException {
        String dateStr = getDateTime_I(date);
        return parseDateStrToDate(dateStr + " " + "00:00:00");
    }

    /**
     * 得到当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前日
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定的年，int格式
     *
     * @param date
     * @return
     */
    public static int getCustomYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得指定的月，int格式
     *
     * @param date
     * @return
     */
    public static int getCustomMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得指定的日(月份中的)，int格式
     *
     * @param date
     * @return
     */
    public static int getCustomDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定的小时(日中的)，int格式
     *
     * @return
     */
    public static int getCustomHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得指定的分钟，int格式
     *
     * @return
     */
    public static int getCustomMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获得指定的秒，int格式
     *
     * @return
     */
    public static int getCustomSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * @param beginDate
     * @param endDate
     * @param f         时间差的形式0:秒,1:分种,2:小时,3:天
     * @return
     */
    public static int getDifferenceNum(Date beginDate, Date endDate, int f) {
        int result = 0;
        if (beginDate == null || endDate == null) {
            return 0;
        }
        try {
            // 日期相减得到日期差X(单位:毫秒)
            long millisecond = endDate.getTime() - beginDate.getTime();
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 比较两个日期的大小,精确到秒
     *
     * @param d1
     * @param d2
     * @return 返回一个long类型的整数，若大于0表示第一个日期晚于第二个日期，小于0表示第一个日期早于第二个日期，否则相等
     * @date 2016-11-22
     */
    public static long compareEachOther(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return -1;
        String d1Str = d1.getTime() + "";
        String d2Str = d2.getTime() + "";
        int l1 = d1Str.length();
        int l2 = d2Str.length();
        d1Str = d1Str.substring(0, l1 - 3) + "000";
        d2Str = d2Str.substring(0, l2 - 3) + "000";
        long long1 = Long.parseLong(d1Str);
        long long2 = Long.parseLong(d2Str);
        return long1 - long2;
    }


}