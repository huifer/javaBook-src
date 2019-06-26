package com.huifer.spring.session.springsession.service;

import com.huifer.spring.session.springsession.table.SysRoleTable;
import com.huifer.spring.session.springsession.vo.ResultVo;
import org.springframework.data.domain.Pageable;

/**
 * <p>Title : SysRoleService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
public interface SysRoleService {

    ResultVo saveSysRole(SysRoleTable sysRoleTable);

    ResultVo selectSysRoleList(String name, Pageable pageable);

    ResultVo selectSysRoleDetail(Integer roleId);

    ResultVo updateSysRole(SysRoleTable sysRoleTable);

    ResultVo deleteSysRole(Integer roleId);

}
