package com.huifer.restsec.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.huifer.restsec.entity.dto.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private Map<Integer, UserInfo> userInfoMap = new ConcurrentHashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger(-1);

    @PutMapping("/")
    public UserInfo editor(@Valid @RequestBody UserInfo userInfo) {
        if (userInfoMap.isEmpty()) {
            return null;
        }
        UserInfo info = userInfoMap.get(Integer.parseInt(userInfo.getId()));
        if (info != null) {
            info.setPwd(userInfo.getPwd());
            info.setName(userInfo.getName());
            info.setUpTime(new Date());
            return info;
        } else {
            return null;
        }
    }

    @PostMapping("/")
    public UserInfo add(@Valid UserInfo userInfo, BindingResult error) {

        if (error.hasErrors()) {
            error.getAllErrors().stream().forEach(
                    s -> {
                        log.error("{}", s);

                    }
            );
        }

        atomicInteger.addAndGet(1);
        userInfo.setId(String.valueOf(atomicInteger.get()));
        userInfo.setRegisterTime(new Date());

        userInfoMap.put(atomicInteger.get(), userInfo);
        log.info("usermap = {}", userInfoMap);
        return userInfo;
    }

    /**
     * @param userInfo
     * @param error    @Valid注解的异常信息
     * @return
     */
    @PostMapping("/2")
    public UserInfo add2(@Valid @RequestBody UserInfo userInfo, BindingResult error) {
        if (error.hasErrors()) {
            error.getAllErrors().stream().forEach(
                    s -> {
                        log.error("{}", s);

                    }
            );
        }

        atomicInteger.addAndGet(1);
        userInfo.setId(String.valueOf(atomicInteger.get()));
        userInfo.setRegisterTime(new Date());

        userInfoMap.put(atomicInteger.get(), userInfo);
        log.info("usermap = {}", userInfoMap);
        return userInfo;
    }

    @JsonView(UserInfo.UserDetailView.class)
    @GetMapping("/{id:\\d+}")
    public UserInfo findById(@PathVariable(value = "id") String id) {
        return userInfoMap.get(Integer.parseInt(id));
    }
}
