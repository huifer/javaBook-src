package com.huifer.spring.session.springsession;

import com.huifer.spring.session.springsession.entity.SysResource;
import com.huifer.spring.session.springsession.service.SysRoleService;
import com.huifer.spring.session.springsession.service.SysUserService;
import com.huifer.spring.session.springsession.table.SysRoleTable;
import com.huifer.spring.session.springsession.vo.ResultVo;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSessionApplicationTests {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void contextLoads() {
//        SysUserTable sysUserTable = new SysUserTable();
//        sysUserTable.setAccount("a");
//        sysUserTable.setPassword("aaaaaaaa");
//        sysUserTable.setForbidden("0");
//        sysUserTable.setName("123");
//
//        ResultVo resultVo = sysUserService.saveSysUser(sysUserTable);
//        System.out.println(resultVo);

        SysResource sysResource = new SysResource();
        sysResource.setName("R1");
        sysResource.setParentId(0);
        sysResource.setPerms("1");

        SysRoleTable sysRoleTable = new SysRoleTable();
        sysRoleTable.setLevel(1);
        sysRoleTable.setName("testrole");
        sysRoleTable.setNote("asda");
        sysRoleTable.setSysResourceList(Collections.singletonList(sysResource));
        ResultVo resultVo = sysRoleService.saveSysRole(sysRoleTable);
        System.out.println(resultVo);
    }

}
