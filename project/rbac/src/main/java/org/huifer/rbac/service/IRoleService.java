package org.huifer.rbac.service;

import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.role.RoleAddReq;
import org.huifer.rbac.entity.req.role.RoleEditorReq;
import org.huifer.rbac.entity.req.role.RoleQueryReq;
import org.huifer.rbac.entity.res.Result;

public interface IRoleService {
    Result delete(IdsReq idsReq);

    Result editor(RoleEditorReq req);

    Result query(RoleQueryReq req, PageReq pageReq);

    Result add(RoleAddReq req);
}
