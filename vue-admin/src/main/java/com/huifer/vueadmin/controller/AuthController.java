package com.huifer.vueadmin.controller;

import com.huifer.vueadmin.entity.db.SysUser;
import org.springframework.web.bind.annotat.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2019-10-18
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public SysUser login(SysUser us) {
        SysUser sysUser = new SysUser();
        Set<String> s = new HashSet<>();
        if (us.getUsername().equals("admin")) {
            s.add("admin");
            sysUser.setRoles(s);

        } else {
            s.add("editor");
            sysUser.setRoles(s);

        }
        return sysUser;
    }
}
