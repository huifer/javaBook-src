package com.huifer.design.proxy.cglib;

import com.huifer.design.proxy.staticproxy.Person;
import java.io.FileOutputStream;
import sun.misc.ProxyGenerator;

/**
 * <p>Title : CglibProxyTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-17
 */
public class CglibProxyTest {

    public static void main(String[] args) throws Exception {

        CGPerson instance = (CGPerson) new CGLIBZhiLian().getInstance(CGPerson.class);
        instance.findWork();
        System.out.println(instance.getClass());



        try {

            byte[] proxyClass = ProxyGenerator
                    .generateProxyClass("CGPerson$$EnhancerByCGLIB$$a7024b7c", new Class[]{Person.class});
            FileOutputStream fos = new FileOutputStream(
                    "E:\\mck\\javaBook-src\\design\\src\\main\\resources\\proxy2.class");

            fos.write(proxyClass);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
