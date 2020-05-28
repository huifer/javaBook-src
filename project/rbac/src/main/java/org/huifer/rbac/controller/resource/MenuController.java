package org.huifer.rbac.controller.resource;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.menu.MenuAddReq;
import org.huifer.rbac.entity.req.resource.menu.MenuEditorReq;
import org.huifer.rbac.entity.req.resource.menu.MenuQueryReq;
import org.huifer.rbac.entity.res.Result;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @PostMapping("/add")
    public Result add(@RequestBody @Validated MenuAddReq req) {
        return null;
    }

    @PostMapping("/query")
    public Result query(
            @RequestBody @Validated MenuQueryReq req,
            @RequestBody @Validated PageReq pageReq
    ) {
        return null;
    }

    @PostMapping("/editor")
    public Result editor(@RequestBody @Validated MenuEditorReq req) {
        return null;
    }

    @PostMapping("/delete")
    public Result delete(
            @RequestBody @Validated IdsReq idsReq
    ) {
        return null;
    }
}
