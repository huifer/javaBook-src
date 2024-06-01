package org.huifer.rbac.controller.resource;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.url.UrlAddReq;
import org.huifer.rbac.entity.req.resource.url.UrlEditorReq;
import org.huifer.rbac.entity.req.resource.url.UrlQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.service.IServerUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server_url")
public class ServerUrlController {
    @Autowired
    private IServerUrlService serverUrlService;

    @PostMapping("/add")
    public Result add(
            @RequestBody @Validated UrlAddReq req
    ) {
        return serverUrlService.add(req);
    }

    @PostMapping("/query")
    public Result query(
            @RequestBody @Validated UrlQueryReq req,
            @RequestBody @Validated PageReq pageReq
    ) {
        return serverUrlService.query(req, pageReq);
    }

    @PostMapping("/editor")
    public Result editor(
            @RequestBody @Validated UrlEditorReq req
    ) {
        return serverUrlService.editor(req);
    }

    @PostMapping("/delete")
    public Result delete(
            @RequestBody @Validated IdsReq idsReq
    ) {
        return serverUrlService.delete(idsReq);
    }

}
