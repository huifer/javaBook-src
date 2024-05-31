package com.huifer.xz.entity;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
public class RabbitMqType {
    public static final String CITY_EXCHANGE = "city_id_exchange";

    /**
     * 存放 {@link CityUrlInfo}
     */
    public static final String CITY_QUEUE_URL = "city_queue_url";
    /**
     * 存放{@link com.huifer.xz.entity.TXz}
     */
    public static final String USER_QUEUE_INFO = "user_queue_info";

    /**
     * 行者路线数据
     */
    public static final String XZ_WO = "xz_wo";

}
