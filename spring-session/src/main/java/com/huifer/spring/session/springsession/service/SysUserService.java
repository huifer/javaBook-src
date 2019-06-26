package com.huifer.spring.session.springsession.service;

import com.huifer.spring.session.springsession.entity.SysUser;
import com.huifer.spring.session.springsession.table.SysUserTable;
import com.huifer.spring.session.springsession.vo.ResultVo;
import org.springframework.data.domain.Pageable;

/**
 * <p>Title : SysUserService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
public interface SysUserService {

    SysUser findByAccount(String account);

    ResultVo saveSysUser(SysUserTable sysUserTable);

    ResultVo selectSysUserList(String name, Pageable pageable);


    ResultVo selectSysUserDetail(Integer userId);

    ResultVo updateSysUser(SysUserTable userTable);

    ResultVo deleteSysUser(Integer userId);


}
