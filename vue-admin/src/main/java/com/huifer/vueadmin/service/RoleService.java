package com.huifer.vueadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huifer.vueadmin.entity.db.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Date: 2019-10-18
 */
public interface RoleService extends IService<SysRole> {
    Set<String> getRoleByUserId(@Param("userId") Integer userId);

}
