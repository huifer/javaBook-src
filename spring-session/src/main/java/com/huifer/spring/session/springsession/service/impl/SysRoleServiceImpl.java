package com.huifer.spring.session.springsession.service.impl;

import com.huifer.spring.session.springsession.entity.SysResource;
import com.huifer.spring.session.springsession.entity.SysRole;
import com.huifer.spring.session.springsession.entity.SysRoleResource;
import com.huifer.spring.session.springsession.repository.SysResourceRepository;
import com.huifer.spring.session.springsession.repository.SysRoleRepository;
import com.huifer.spring.session.springsession.repository.SysRoleResourceRepository;
import com.huifer.spring.session.springsession.service.SysRoleService;
import com.huifer.spring.session.springsession.table.SysRoleTable;
import com.huifer.spring.session.springsession.util.ResultUtil;
import com.huifer.spring.session.springsession.vo.ResultVo;
import com.huifer.spring.session.springsession.vo.SysRoleVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>Title : SysRoleServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Service
@Transactional
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysRoleResourceRepository sysRoleResourceRepository;
    @Autowired
    private SysResourceRepository sysResourceRepository;

    public static String like(String column) {
        StringBuilder sb = new StringBuilder("%" + column + "%");
        return sb.toString();
    }

    @Override
    public ResultVo saveSysRole(SysRoleTable sysRoleTable) {
        SysRole sysRole = new SysRole();

        BeanUtils.copyProperties(sysRoleTable, sysRole);
        SysRole sysRoleSave = sysRoleRepository.save(sysRole);
        log.info("role save : {}" + sysRoleSave);

        List<SysResource> sysResourceList = sysRoleTable.getSysResourceList();
        List<SysResource> resources = sysResourceRepository.saveAll(sysResourceList);
        log.info("资源跟新 = {}" + resources);

        List<SysRoleResource> sysRoleResourceList = new ArrayList<>();
        sysRoleTable.getSysResourceList().forEach(
                rs -> {
                    SysRoleResource sysRoleResource = new SysRoleResource();
                    sysRoleResource.setRoleId(sysRoleSave.getId());
                    sysRoleResource.setResourceId(rs.getId());
                    sysRoleResourceList.add(sysRoleResource);

                }
        );

        List<SysRoleResource> sysRoleResources = sysRoleResourceRepository
                .saveAll(sysRoleResourceList);
        log.info("角色资源保存 = {}" + sysRoleResources);
        return ResultUtil.success();
    }

    @Override
    public ResultVo selectSysRoleList(String name, Pageable pageable) {
        Page<SysRole> sysRolePage = sysRoleRepository.findAll(
                (Specification<SysRole>) (root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (StringUtils.isEmpty(name)) {
                        predicates.add(criteriaBuilder
                                .like(root.get("name").as(String.class), like(name)));
                    }
                    Predicate[] predicates1 = new Predicate[predicates.size()];
                    return criteriaQuery.where(predicates.toArray(predicates1)).getRestriction();
                }, pageable);
        return ResultUtil.success(sysRolePage);

    }

    @Override
    public ResultVo selectSysRoleDetail(Integer roleId) {
        SysRoleVo sysRoleVo = new SysRoleVo();
        SysRole sysRole = sysRoleRepository.findById(roleId).get();
        BeanUtils.copyProperties(sysRole, sysRoleVo);

        List<SysRoleResource> byRoleId = sysRoleResourceRepository.findByRoleId(roleId);
        List<Integer> sysResourceIds = new ArrayList<>();
        byRoleId.forEach(ro -> {
            sysResourceIds.add(ro.getResourceId());
        });

        List<SysResource> all = sysResourceRepository.findAllById(sysResourceIds);
        sysRoleVo.setResourceList(all);
        log.info("角色信息 = {} " + sysRoleVo);

        return ResultUtil.success(sysRoleVo);
    }

    @Override
    public ResultVo updateSysRole(SysRoleTable sysRoleTable) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleTable, sysRole);
        sysRoleResourceRepository.deleteByRoleId(sysRoleTable.getId());

        SysRole save = sysRoleRepository.save(sysRole);
        log.info("角色更新 = {}" + save);
        List<SysRoleResource> sysRoleResources = new ArrayList<>();
        sysRoleTable.getSysResourceList().forEach(rs -> {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setResourceId(rs.getId());
            sysRoleResource.setRoleId(sysRoleTable.getId());
            sysRoleResources.add(sysRoleResource);
        });
        List<SysRoleResource> sysRoleResources1 = sysRoleResourceRepository
                .saveAll(sysRoleResources);
        log.info("资源更新 = {}" + sysRoleResources1);
        return ResultUtil.success();
    }

    @Override
    public ResultVo deleteSysRole(Integer roleId) {
        sysRoleRepository.deleteById(roleId);
        sysRoleResourceRepository.deleteByRoleId(roleId);

        return ResultUtil.success();
    }
}
