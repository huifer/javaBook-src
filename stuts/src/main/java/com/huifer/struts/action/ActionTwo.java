package com.huifer.struts.action;

import com.opensymphony.xwork2.Action;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-21
 */
public class ActionTwo implements Action {
    @Override
    public String execute() throws Exception {
        System.out.println("action2");
        return null;
    }
}
