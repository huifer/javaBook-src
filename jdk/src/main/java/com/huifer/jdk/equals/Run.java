package com.huifer.jdk.equals;

/**
 * <p>Title : Run </p>
 * <p>Description : == 和 equals</p>
 *
 * @author huifer
 * @date 2019-05-24
 */
public class Run {

    public static void main(String[] args) {
        // == 对于基本类型 int long  double float boolean short 来说对比较的是值是否相等
        int i = 1;
        int j = 1;
        System.out.println(i == j);

        // == 对于引用类型 比较的是引用是否相同
        // String 的创建方式 直接等于字符串 或者 new String(字符串)
        // 直接创建的字符串他们的引用地址相同 ， 而 new 创建的字符串是开辟新的内存空间
        String a = "b";
        String b = "b";
        System.out.println(a == b);
        String c = new String("b") ;
        System.out.println(a == c);

        // equlas
        System.out.println(a.equals(c));

        Eq eq1 = new Eq(1);
        Eq eq2 = new Eq(1);

        System.out.println(eq1.equals(eq2));
    }


    private static class Eq{
        public int anInt;

        public Eq(int anInt) {
            this.anInt = anInt;
        }
    }
}
