package com.huifer.restsec.controller;

import com.huifer.restsec.entity.dto.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @GetMapping("/hello")
    public List<UserInfo> hello() {
        List<UserInfo> userInfos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(UUID.randomUUID().toString());
            userInfos.add(
                    userInfo
            );
        }
        return userInfos;
    }
}
