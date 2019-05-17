package com.huifer.design.proxy.jdk;

/**
 * <p>Title : JdkProxyTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        PersonJdk pjd = new PersonJdk();
        Object obj = new ZhiLianJdk().getInstance(pjd);
        // 注意 JDK 代理实现的是接口 并不是实现了接口的类 (PersonJdk)
        ZhiYuan p = (ZhiYuan) obj;
        p.findWork();
//        work58(pjd);

        System.out.println(p.getClass());


    }

    private static void work58(PersonJdk pjd) {
        System.out.println("===========");
        ZhiYuan instance = (ZhiYuan) new Jdk58().getInstance(pjd);
        instance.findHouse();
    }

}
