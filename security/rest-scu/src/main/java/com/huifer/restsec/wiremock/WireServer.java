package com.huifer.restsec.wiremock;

import com.alibaba.fastjson.JSON;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.huifer.restsec.entity.dto.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
public class WireServer {
    public static void main(String[] args) {
        WireMock.configureFor("localhost", 8081);
        WireMock.removeAllMappings();

        String urlRegex = "/user/\\d+";
        UserInfo userInfo = new UserInfo();
        userInfo.setUpTime(new Date());
        userInfo.setId("0");
        userInfo.setPwd("1");
        userInfo.setName("name");
        userInfo.setRegisterTime(new Date());
        urlPath(urlRegex, userInfo);

        urlRegex = "/user/";
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(userInfo);
        userInfos.add(userInfo);
        urlPath(urlRegex, userInfos);
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
}
