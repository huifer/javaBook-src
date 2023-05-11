package org.huifer.rbac.service;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.url.UrlAddReq;
import org.huifer.rbac.entity.req.resource.url.UrlEditorReq;
import org.huifer.rbac.entity.req.resource.url.UrlQueryReq;
import org.huifer.rbac.entity.res.Result;

public interface IServerUrlService {
    Result add(UrlAddReq req);

    Result query(UrlQueryReq req, PageReq pageReq);

    Result editor(UrlEditorReq req);

    Result delete(IdsReq idsReq);
}
