package com.huifer.comment;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
public class SpringComment {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring_comment_config.xml");
    }


    @Test
    public void testCommentDemo01() {
        UserService bean = context.getBean(UserService.class);
        bean.saveUser();

    }

}
