package com.huifer.jdk.reflect;

import java.lang.reflect.Method;

/**
 * <p>Title : Run </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class Run {

    public static void main(String[] args) throws Exception {
        Cc cccc = new Cc();

        Class<? extends Cc> aClass = cccc.getClass();

        Method setAname = aClass.getDeclaredMethod("setAname", String.class);

        Method sayHi = aClass.getDeclaredMethod("sayHi", null);
        sayHi.setAccessible(true);

        setAname.invoke(cccc, "张三");
        sayHi.invoke(cccc, null);

    }

    private static class Cc {

        private String aname;

        private void sayHi() {
            System.out.println("hello" + aname);
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }

}
