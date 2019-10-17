package com.huifer.restsec.wiremock;

import com.alibaba.fastjson.JSON;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-09
 */
@Data
public class TestWireEntity {
    private String aString;
    private Double aDouble;
    private Integer anInt;
    private Long aLong;
    private Short aShort;
    private Float aFloat;
    private Date adte;
    private Boolean aBoolean;

    public static void main(String[] args) throws Exception {

        TestWireEntity model = new TestWireEntity();

        hc(model);

        System.out.println();
        WireMock.configureFor("47.98.225.144", 9090);
        urlPath("/user/\\d+", model);
    }
    private static void urlPath(String urlRegex, Object obj) {
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathMatching(urlRegex))
                        .willReturn(WireMock.aResponse()
                                .withBody(JSON.toJSONString(obj))
                                .withStatus(200)
                        )
        );

    }

    private static void hc(Object bean) throws Exception {
        Class userCla = bean.getClass();
        Field[] fs = userCla.getDeclaredFields();
        Random r = new Random();

        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);

            String type = f.getType().toString();
            if (type.endsWith("String")) {
                f.set(bean, "12");
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                f.set(bean, r.nextInt());
            } else if (type.endsWith("long") || type.endsWith("Long")) {
                f.set(bean, r.nextLong());
            } else if (type.endsWith("float") || type.endsWith("Float")) {
                f.set(bean, r.nextFloat());
            } else if (type.endsWith("double") || type.endsWith("Double")) {
                f.set(bean, r.nextDouble());
            } else if (type.endsWith("boolean") || type.endsWith("Boolean")) {
                f.set(bean, r.nextBoolean());
            } else if (type.endsWith("short") || type.endsWith("Short")) {
                f.set(bean, Short.valueOf("1"));
            } else if (type.endsWith("Date")) {
                f.set(bean, new Date());
            } else {
                System.out.println(f.getType() + "\t");
            }

        }
    }
}
