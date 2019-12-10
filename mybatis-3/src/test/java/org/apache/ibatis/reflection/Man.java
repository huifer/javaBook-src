package org.apache.ibatis.reflection;

import java.util.ArrayList;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-12-09
 */
public class Man extends People implements TestManInterface {
    @Override
    public Integer inte() {
        return 1;
    }

    public String hello() {
        return "hello";
    }

    public ArrayList<String> getlist() {
        return null;
    }
}
