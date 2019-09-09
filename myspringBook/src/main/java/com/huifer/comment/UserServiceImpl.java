package com.huifer.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
@Component(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BeanT bt;


    @Resource(name = "bt")
    private BeanT btt;

    @Value(value = "1222")
    private int dc;
    @Value(value = "${data.pc}")
    private String pc;

    @Override
    public void saveUser() {
        System.out.println(bt);
        System.out.println(btt.i);
        System.out.println(dc);
        System.out.println(pc);
        System.out.println("注解配置");
    }

}
