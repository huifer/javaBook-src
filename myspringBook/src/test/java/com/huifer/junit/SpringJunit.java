package com.huifer.junit;

import com.huifer.comment.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_comment_config.xml"})
public class SpringJunit {


    @Resource
    ApplicationContext ctx;

    @Autowired
    private UserService userService;


    @Test
    public void demo() {
        userService.saveUser();
    }
}
