package com.huifer.spring.session.springsession.service.impl;

import com.huifer.spring.session.springsession.entity.SysResource;
import com.huifer.spring.session.springsession.entity.SysRoleResource;
import com.huifer.spring.session.springsession.entity.SysUserRole;
import com.huifer.spring.session.springsession.repository.SysResourceRepository;
import com.huifer.spring.session.springsession.repository.SysRoleResourceRepository;
import com.huifer.spring.session.springsession.repository.SysUserRoleRepository;
import com.huifer.spring.session.springsession.service.SysResourceService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>Title : SysResourceServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Service
@Transactional
@Slf4j
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceRepository sysResourceRepository;
    @Autowired
    SysUserRoleRepository sysUserRoleRepository;
    @Autowired
    SysRoleResourceRepository sysRoleResourceRepository;


    @Override
    public Set<String> selectUserPerms(Integer userId) {
        List<SysUserRole> userRoles = sysUserRoleRepository.findByUserId(userId);
        List<Integer> roleIds = new ArrayList<>();

        userRoles.forEach(ur -> {
            roleIds.add(ur.getRoleId());
        });

        List<SysRoleResource> roleResource = sysRoleResourceRepository.findByRoleId(roleIds);
        List<Integer> resourceIds = new ArrayList<>();
        roleResource.forEach(
                r -> {
                    resourceIds.add(r.getResourceId());
                }
        );

        Set<String> prems = new HashSet<>();
        List<SysResource> resources = sysResourceRepository.findAllById(resourceIds);
        resources.forEach(rs -> {
            if (StringUtils.isEmpty(rs.getPerms())) {
                prems.add(rs.getPerms());
            }
        });
        return prems;
    }
}
