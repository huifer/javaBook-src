package org.huifer.rbac.controller;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.role.RoleAddReq;
import org.huifer.rbac.entity.req.role.RoleEditorReq;
import org.huifer.rbac.entity.req.role.RoleQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    public Result add(
            @RequestBody @Validated RoleAddReq req
    ) {
        return roleService.add(req);
    }

    @PostMapping("/query")
    public Result query(
            @RequestBody @Validated RoleQueryReq req,
            @RequestBody @Validated PageReq pageReq
    ) {

        return roleService.query(req, pageReq);
    }

    @PostMapping("/editor")
    public Result editor(@RequestBody @Validated RoleEditorReq req) {
        return roleService.editor(req);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated IdsReq idsReq) {
        return roleService.delete(idsReq);
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
