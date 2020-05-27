package org.huifer.rbac.controller;

import javax.validation.Valid;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.user.UserAddReq;
import org.huifer.rbac.entity.req.user.UserBindRoleReq;
import org.huifer.rbac.entity.req.user.UserEditorReq;
import org.huifer.rbac.entity.req.user.UserQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    IUserService userService;

    public UserController(@Qualifier("userService") IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Result add(
            @RequestBody @Valid UserAddReq req
    ) {
        return userService.add(req);
    }

    @PostMapping("/query")
    public Result query(
            @RequestBody @Validated UserQueryReq req,
            @RequestBody @Validated PageReq pageReq
    ) {

        return userService.query(req, pageReq);
    }

    @PostMapping("/editor")
    public Result editor(
            @RequestBody @Validated UserEditorReq req
    ) {
        return userService.editor(req);

    }

    @PostMapping("/delete")
    public Result delete(
            @RequestBody @Validated IdsReq req
    ) {
        return userService.delete(req);

    }

    @PostMapping("/setting_role")
    public Result settingRole(
            @RequestBody @Validated UserBindRoleReq req
    ) {
        return userService.settingRole(req);
    }

}
