package com.huifer.spring.session.springsession.controller;

import com.huifer.spring.session.springsession.service.SysUserService;
import com.huifer.spring.session.springsession.table.SysUserTable;
import com.huifer.spring.session.springsession.vo.ResultVo;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title : SysUserController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @RequiresPermissions("sys:user:insert")
    @PostMapping("/saveUser")
    public ResultVo saveUser(@Valid @RequestBody SysUserTable sysUserTable
    ) {

        return sysUserService.saveSysUser(sysUserTable);
    }


    @RequiresPermissions("sys:user:list")
    @GetMapping("/selectUserList")
    public ResultVo selectUserList(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "name", defaultValue = "") String name) {
        PageRequest pageRequest = new PageRequest(page, size);
        return sysUserService.selectSysUserList(name, pageRequest);
    }

    @RequiresPermissions("sys:user:detail")
    @GetMapping("/selectUserDetail")
    public ResultVo selectUserDetail(@RequestParam(value = "id", required = false) Integer id) {
        return sysUserService.selectSysUserDetail(id);
    }

    @RequiresPermissions("sys:user:update")
    @PutMapping("/updateUser")
    public ResultVo updateUser(@Valid @RequestBody SysUserTable sysUserTable
    ) {
        return sysUserService.updateSysUser(sysUserTable);
    }

    @RequiresPermissions("sys:user:delete")
    @DeleteMapping("/deleteUser/{id}")
    public ResultVo deleteUser(@PathVariable Integer id) {
        return sysUserService.deleteSysUser(id);
    }
}
