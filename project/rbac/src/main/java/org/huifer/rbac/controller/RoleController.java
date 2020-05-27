package org.huifer.rbac.controller;

import org.huifer.rbac.entity.res.Result;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @PostMapping("/add")
    public Result add() {
        return null;
    }

    @PostMapping("/query")
    public Result query() {
        return null;
    }

    @PostMapping("/editor")
    public Result editor() {
        return null;
    }

    @PostMapping("/delete")
    public Result delete() {
        return null;
    }

    @PostMapping("/setting_btn")
    public Result settingBtn() {
        return null;
    }

    @PostMapping("/setting_menu")
    public Result settingMenu() {
        return null;
    }

    @PostMapping("/setting_url")
    public Result settingUrl() {
        return null;
    }
}
