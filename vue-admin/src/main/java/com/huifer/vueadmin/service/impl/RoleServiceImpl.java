package com.huifer.vueadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huifer.vueadmin.entity.db.SysRole;
import com.huifer.vueadmin.entity.db.SysUser;
import com.huifer.vueadmin.mapper.SysRoleMapper;
import com.huifer.vueadmin.mapper.SysUserMapper;
import com.huifer.vueadmin.service.RoleService;

import java.util.Set;

/**
 * @Date: 2019-10-18
 */
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements RoleService {
    @Override
    public Set<String> getRoleByUserId(Integer userId) {
        return this.baseMapper.getRoleByUserId(userId);
    }
}
