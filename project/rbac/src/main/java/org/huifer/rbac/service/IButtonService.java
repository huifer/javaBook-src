package org.huifer.rbac.service;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.btn.BtnAddReq;
import org.huifer.rbac.entity.req.resource.btn.BtnEditorReq;
import org.huifer.rbac.entity.req.resource.btn.BtnQueryReq;
import org.huifer.rbac.entity.res.Result;

public interface IButtonService {
    Result add(BtnAddReq req);

    Result query(BtnQueryReq req, PageReq pageReq);

    Result editor(BtnEditorReq req);

    Result delete(IdsReq idsReq);
}
