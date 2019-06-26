package com.huifer.spring.session.springsession.controller;

import com.huifer.spring.session.springsession.service.SysRoleService;
import com.huifer.spring.session.springsession.table.SysRoleTable;
import com.huifer.spring.session.springsession.vo.ResultVo;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
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
 * <p>Title : SysRoleController </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @RequiresPermissions("sys:role:insert")
    @PostMapping("/saveRole")
    public ResultVo saveRole(@Valid @RequestBody SysRoleTable sysRoleTable
             ) {
        return sysRoleService.saveSysRole(sysRoleTable);
    }


    @RequiresPermissions("sys:role:list")
    @GetMapping("/selectRoleList")
    public ResultVo selectRoleList(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "name", defaultValue = "") String name) {

        PageRequest pageRequest = new PageRequest(page, size);
        return sysRoleService.selectSysRoleList(name, pageRequest);
    }


    @RequiresPermissions("sys:role:detail")
    @GetMapping("/selectRoleDetail")
    public ResultVo selectRoleDetail(@RequestParam(value = "id", required = false) Integer id) {

        return sysRoleService.selectSysRoleDetail(id);

    }

    @RequiresPermissions("sys:role:update")
    @PutMapping("/updateRole")
    public ResultVo updateRole(@Valid @RequestBody SysRoleTable sysRoleTable,
            BindingResult bindingResult) {

        return sysRoleService.updateSysRole(sysRoleTable);
    }

    @RequiresPermissions("sys:role:delete")
    @DeleteMapping("/deleteRole/{id}")
    public ResultVo deleteRole(@PathVariable Integer id) {
        return sysRoleService.deleteSysRole(id);
    }

}
