package com.huifer.aop;

import com.huifer.aop.advice.BaseAopPointCutInAdvice;
import com.huifer.aop.advisor.WoMan;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
public class SpringAop {

    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("spring_config.xml");

    }


    @Test
    public void testAopDemo01() {
        BaseAopPointCutInAdvice cut = (BaseAopPointCutInAdvice) context.getBean("personProxy");
        cut.eat();
    }


    @Test
    public void testAopDemo02() {
        System.out.println("************************************************");
        WoMan woMan = (WoMan) context.getBean("woManProxy");
        System.out.println("==================女性吃饭==================");
        woMan.eat();
        System.out.println("==================女性上厕所==================");
        woMan.wc();

    }
}
