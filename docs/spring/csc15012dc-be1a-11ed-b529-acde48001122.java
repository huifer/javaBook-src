package com.huifer.struts.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-21
 */
public class UserAction extends ActionSupport {
    public String find() {
        System.out.println("find user");
        return NONE;
    }

    public String update() {
        System.out.println("find update");
        return NONE;
    }

    public String delete() {
        System.out.println("find delete");
        return NONE;
    }

    public String save() {
        System.out.println("find save");
        return NONE;
    }
}
