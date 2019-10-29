package com.huifer.xz.entity;

import lombok.Data;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@Data
public class CityUrlInfo {
    private String cName;
    private String url;
    private Boolean isWork;
    private Integer cityId;
    private Integer page;
    private String redisKey;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"cName\":\"")
                .append(cName).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"isWork\":")
                .append(isWork);
        sb.append(",\"cityId\":")
                .append(cityId);
        sb.append(",\"page\":")
                .append(page);
        sb.append(",\"redisKey\":\"")
                .append(redisKey).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
