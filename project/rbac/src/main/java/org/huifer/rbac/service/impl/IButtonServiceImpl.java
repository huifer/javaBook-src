package org.huifer.rbac.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.huifer.rbac.entity.db.TBtn;
import org.huifer.rbac.entity.enums.ErrorResult;
import org.huifer.rbac.entity.enums.OkResult;
import org.huifer.rbac.entity.req.IdsReq;
import org.huifer.rbac.entity.req.PageReq;
import org.huifer.rbac.entity.req.resource.btn.BtnAddReq;
import org.huifer.rbac.entity.req.resource.btn.BtnEditorReq;
import org.huifer.rbac.entity.req.resource.btn.BtnQueryReq;
import org.huifer.rbac.entity.res.Result;
import org.huifer.rbac.mapper.TBtnMapper;
import org.huifer.rbac.service.IButtonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IButtonServiceImpl implements IButtonService {
    @Autowired
    private TBtnMapper btnMapper;

    @Override
    public Result add(BtnAddReq req) {
        int insert = this.btnMapper.insert(req.convert());
        return (insert > 0) ? OkResult.INSERT.to(Boolean.TRUE) : ErrorResult.INSERT.to(Boolean.FALSE);
    }

    @Override
    public Result query(BtnQueryReq req, PageReq pageReq) {
        IPage<TBtn> page = new Page<>(pageReq.getNum(), pageReq.getSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(req.getName())) {
            queryWrapper.like(TBtn.COL_NAME, req.getName());
        }
        if (StringUtils.isNotBlank(req.getType())) {
            queryWrapper.like(TBtn.COL_TYPE, req.getType());
        }
        IPage iPage = this.btnMapper.selectPage(page, queryWrapper);
        return OkResult.QUERY.to(iPage);
    }

    @Override
    public Result editor(BtnEditorReq req) {
        if (req.getId() != null) {
            TBtn tBtn = this.btnMapper.selectById(req.getId());
            if (StringUtils.isNotBlank(req.getType())) {
                tBtn.setType(req.getType());
            }
            if (StringUtils.isNotBlank(req.getName())) {
                tBtn.setName(req.getName());
            }
            int update = this.btnMapper.updateById(tBtn);
            return (update > 0) ? OkResult.UPDATE.to(Boolean.TRUE) : ErrorResult.UPDATE.to(Boolean.FALSE);
        }
        throw new IllegalArgumentException("当前id不存在");

    }

    @Override
    public Result delete(IdsReq idsReq) {
        if (idsReq != null) {
            List<Long> ids = idsReq.getIds();
            if (!ids.isEmpty()) {
                int del = this.btnMapper.deleteBatchIds(ids);
                return (del > 0) ? OkResult.DELETE.to(Boolean.TRUE) : ErrorResult.DELETE.to(Boolean.FALSE);

            }
        }
        throw new IllegalArgumentException("参数不存在");

    }
}
