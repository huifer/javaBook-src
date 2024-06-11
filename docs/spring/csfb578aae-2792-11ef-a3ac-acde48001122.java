package com.huifer.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.huifer.security.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @GetMapping("/hello")
    @JsonView(UserInfo.UserSimpleView.class)
    public List<UserInfo> hello() {
        List<UserInfo> userInfos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(UUID.randomUUID().toString());
            userInfo.setPwd(UUID.randomUUID().toString());
            userInfos.add(
                    userInfo
            );
        }
        return userInfos;
    }

    @JsonView(UserInfo.UserDetailView.class)
    @GetMapping("/user/{id:\\d+}")
    public UserInfo findById(@PathVariable(value = "id") String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(String.valueOf(id));
        userInfo.setPwd(String.valueOf(id));

        return userInfo;
    }
}
