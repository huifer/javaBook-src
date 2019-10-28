package com.huifer.xz.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@Data
public class CityInfo implements Serializable {

    /**
     * 城市id
     */
    private int cityId;

    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 这个城市有几页
     */
    private int pageSize;

    @Override
    public String toString() {
        return "CityInfo{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
