package org.huifer.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.huifer.rbac.entity.db.TUser;
import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.user.UserAddReq;
import org.huifer.rbac.entity.req.user.UserBindRoleReq;
import org.huifer.rbac.entity.req.user.UserEditorReq;
import org.huifer.rbac.entity.req.user.UserQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.entity.res.user.UserQueryRes;

public interface IUserService {
    Result<Boolean> settingRole(UserBindRoleReq req);

    Result<Boolean> delete(IdsReq req);

    Result<Boolean> editor(UserEditorReq req);

    Result<Page<UserQueryRes>> query(UserQueryReq req, PageReq pageReq);

    Result<Boolean> add(UserAddReq req);
}
