package com.huifer.aop.aspects;

import com.huifer.comment.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_comment_config.xml"})
public class MyAdviceTest {
    @Autowired
    private RoleService roleService;


    @Test
    public void demo() {
        roleService.saveRole();
    }
}