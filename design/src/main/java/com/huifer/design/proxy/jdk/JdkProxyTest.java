//package com.huifer.design.proxy.jdk;
//
//import com.huifer.design.proxy.staticproxy.Person;
//import sun.misc.ProxyGenerator;
//
//import java.io.FileOutputStream;
//
///**
// * <p>Title : JdkProxyTest </p>
// * <p>Description : </p>
// *
// * @author huifer
// * @date 2019-05-17
// */
//public class JdkProxyTest {
//
//    public static void main(String[] args) throws Exception {
//        PersonJdk pjd = new PersonJdk();
//        Object obj = new ZhiLianJdk().getInstance(pjd);
//        // 注意 JDK 代理实现的是接口 并不是实现了接口的类 (PersonJdk)
//        ZhiYuan p = (ZhiYuan) obj;
//        p.findWork();
////        work58(pjd);
//
//        System.out.println(p.getClass());
//
////        persistence();
//
//    }
//
//    /**
//     * 持久化本地
//     */
//    private static void persistence() {
//        try {
//            // 需要提前知道代理类的名字
//            byte[] proxyClass = ProxyGenerator
//                    .generateProxyClass("$Proxy0", new Class[]{Person.class});
//            FileOutputStream fos = new FileOutputStream(
//                    "E:\\mck\\javaBook-src\\design\\src\\main\\resources\\proxy.class");
//
//            fos.write(proxyClass);
//            fos.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    private static void work58(PersonJdk pjd) {
//        System.out.println("===========");
//        ZhiYuan instance = (ZhiYuan) new Jdk58().getInstance(pjd);
//        instance.findHouse();
//    }
//
//}
