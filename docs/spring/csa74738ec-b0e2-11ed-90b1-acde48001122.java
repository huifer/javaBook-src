package org.huifer.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.huifer.rbac.entity.db.TUser;
import org.huifer.rbac.entity.enums.ErrorResult;
import org.huifer.rbac.entity.enums.OkResult;
import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.user.UserAddReq;
import org.huifer.rbac.entity.req.user.UserBindRoleReq;
import org.huifer.rbac.entity.req.user.UserEditorReq;
import org.huifer.rbac.entity.req.user.UserQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.entity.res.user.UserQueryRes;
import org.huifer.rbac.mapper.TUserMapper;
import org.huifer.rbac.service.IUserService;
import org.huifer.rbac.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userService")
public class IUserServiceImpl implements IUserService {
    final
    TUserMapper userMapper;

    public IUserServiceImpl(TUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<Boolean> settingRole(UserBindRoleReq req) {
        return null;
    }

    @Override
    public Result<Boolean> delete(IdsReq req) {
        if (req != null) {
            List<Long> ids = req.getIds();
            if (!ids.isEmpty()) {
                int del = this.userMapper.deleteBatchIds(ids);
                return (del > 0) ? OkResult.DELETE.to(Boolean.TRUE) : ErrorResult.DELETE.to(Boolean.FALSE);

            }
        }
        throw new IllegalArgumentException("参数不存在");
    }

    @Override
    public Result<Boolean> editor(UserEditorReq req) {
        if (req != null) {
            TUser tUser = userMapper.selectById(req.getId());
            if (tUser != null) {
                if (StringUtils.isNotBlank(req.getUsername())) {
                    tUser.setUserName(req.getUsername());
                }
                if (StringUtils.isNotBlank(req.getPassword())) {
                    tUser.setPassword(Md5Util.MD5(req.getPassword()));
                }
                int update = this.userMapper.updateById(tUser);
                return (update > 0) ? OkResult.UPDATE.to(Boolean.TRUE) : ErrorResult.UPDATE.to(Boolean.FALSE);

            }
        }
        throw new IllegalArgumentException("当前id用户名不存在");
    }

    @Override
    public Result<Page<UserQueryRes>> query(UserQueryReq req, PageReq pageReq) {
        Page page = new Page<>(pageReq.getNum(), pageReq.getSize());

        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(req.getUsername())) {
            queryWrapper.like(TUser.COL_USER_NAME, req.getUsername());
        }
        queryWrapper.orderByDesc(TUser.COL_CREATE_TIME);

        Page tUserPage = this.userMapper.selectPage(page, queryWrapper);
        List<UserQueryRes> res = new ArrayList<>();

        List<TUser> records = tUserPage.getRecords();
        for (TUser record : records) {
            UserQueryRes userQueryRes = new UserQueryRes();
            BeanUtils.copyProperties(record, userQueryRes);
            res.add(userQueryRes);
        }
        tUserPage.setRecords(res);
        return OkResult.QUERY.to(tUserPage);
    }

    @Override
    public Result<Boolean> add(UserAddReq req) {
        TUser convert = req.convert();
        convert.setCreateTime(LocalDateTime.now());
        convert.setUpdateTime(LocalDateTime.now());
        // todo: 2020/5/27 设置create_user
        int insert = userMapper.insert(convert);
        return (insert > 0) ? OkResult.INSERT.to(Boolean.TRUE) : ErrorResult.INSERT.to(Boolean.FALSE);
    }
}
