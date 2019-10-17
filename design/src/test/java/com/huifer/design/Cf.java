package com.huifer.design;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-20
 */
public class Cf {


    private Boolean conn = false;
    private boolean isClose = false;

    @Before
    public void init() {
        conn = true;
        System.out.println("数据连接建立");
    }

    @Test
    public void test00() {
        System.out.println("操作增删改查 ");
    }

    @Test
    public void test01() {
        isClose = true;
        System.out.println("操作结束是否关闭");
    }

    @After
    public void del() {
        if (isClose) {
            System.out.println("关闭了数据库连接");
            conn = false;
            System.out.println(conn);
        } else {
            System.out.println("我没有关闭");
        }
    }

}
