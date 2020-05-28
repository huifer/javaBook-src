package org.huifer.rbac.controller.resource;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.btn.BtnAddReq;
import org.huifer.rbac.entity.req.resource.btn.BtnEditorReq;
import org.huifer.rbac.entity.req.resource.btn.BtnQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.service.IButtonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/button")
public class ButtonController {
    @Autowired
    private IButtonService buttonService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated BtnAddReq req) {
        return buttonService.add(req);
    }

    @PostMapping("/query")
    public Result query(
            @RequestBody @Validated BtnQueryReq req,
            @RequestBody @Validated PageReq pageReq
    ) {
        return buttonService.query(req, pageReq);
    }

    @PostMapping("/editor")
    public Result editor(@RequestBody @Validated BtnEditorReq req) {
        return buttonService.editor(req);
    }

    @PostMapping("/delete")
    public Result delete(
            @RequestBody @Validated IdsReq idsReq
    ) {
        return buttonService.delete(idsReq);
    }

}
