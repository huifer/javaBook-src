package org.huifer.rbac.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.huifer.rbac.entity.db.TUrl;
import org.huifer.rbac.entity.enums.ErrorResult;
import org.huifer.rbac.entity.enums.OkResult;
import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.url.UrlAddReq;
import org.huifer.rbac.entity.req.resource.url.UrlEditorReq;
import org.huifer.rbac.entity.req.resource.url.UrlQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.mapper.TUrlMapper;
import org.huifer.rbac.service.IServerUrlService;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IServerUrlServiceImpl implements IServerUrlService {
    private final TUrlMapper urlMapper;

    public IServerUrlServiceImpl(TUrlMapper urlMapper) {
        this.urlMapper = urlMapper;
    }

    @Override
    public Result add(UrlAddReq req) {
        int insert = this.urlMapper.insert(req.convert());
        return (insert > 0) ? OkResult.INSERT.to(Boolean.TRUE) : ErrorResult.INSERT.to(Boolean.FALSE);
    }

    @Override
    public Result query(UrlQueryReq req, PageReq pageReq) {
        IPage<TUrl> page = new Page<>(pageReq.getNum(), pageReq.getSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(req.getUrl())) {
            queryWrapper.like(TUrl.COL_URL, req.getUrl());
        }
        if (StringUtils.isNotBlank(req.getContext())) {
            queryWrapper.like(TUrl.COL_CONTEXT, req.getContext());
        }
        IPage iPage = this.urlMapper.selectPage(page, queryWrapper);
        return OkResult.QUERY.to(iPage);
    }

    @Override
    public Result editor(UrlEditorReq req) {
        if (req.getId() != null) {
            TUrl tUrl = this.urlMapper.selectById(req.getId());
            if (StringUtils.isNotBlank(req.getContext())) {
                tUrl.setContext(req.getContext());
            }
            if (StringUtils.isNotBlank(req.getUrl())) {
                tUrl.setUrl(req.getUrl());
            }
            int update = this.urlMapper.updateById(tUrl);
            return (update > 0) ? OkResult.UPDATE.to(Boolean.TRUE) : ErrorResult.UPDATE.to(Boolean.FALSE);
        }
        throw new IllegalArgumentException("当前id不存在");

    }

    @Override
    public Result delete(IdsReq idsReq) {
        if (idsReq != null) {
            List<Long> ids = idsReq.getIds();
            if (!ids.isEmpty()) {
                int del = this.urlMapper.deleteBatchIds(ids);
                return (del > 0) ? OkResult.DELETE.to(Boolean.TRUE) : ErrorResult.DELETE.to(Boolean.FALSE);

            }
        }
        throw new IllegalArgumentException("参数不存在");
    }
}
