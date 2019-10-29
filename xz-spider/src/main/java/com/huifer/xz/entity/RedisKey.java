package com.huifer.xz.entity;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
public class RedisKey {
    /**
     * 存放城市信息
     */
    public static final String CITY_KEY = "xz:city:";
    /**
     * 获取城市信息失败的url
     */
    public static final String CITY_INFO_ERROR_URL = "xz:city:url:error";


    /**
     * 城市爬虫url 存放{@link CityUrlInfo} 原始数据
     */
    public static final String CITY_URL_LIST = "xz:city:url:";

    /**
     * 存放爬取城市下用户数据失败的城市数据{@link CityUrlInfo}
     */
    public static final String CITY_URL_LIST_ERROR = "xz:city:url_error:";
    /**
     * 存放爬取城市下用户数据成功的城市数据{@link CityUrlInfo}
     */
    public static final String CITY_URL_LIST_SUCCESS = "xz:city:url_success:";


    /**
     * 存放爬取城市下用户数据成功的数据{@link TXz}
     */
    public static final String XZ_USER_URL = "xz:user:info:";

    /**
     * 行者月数据
     */
    public static final String XZ_USER_MONTH = "xz:user:month";

    public static final String XZ_LINE = "xz:line:success";
    public static final String XZ_LINE_ERROR = "xz:line:error:";

}
