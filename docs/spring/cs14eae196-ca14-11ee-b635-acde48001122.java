package com.huifer.struts.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-21
 */
public class ActionThree extends ActionSupport {
    @Override
    public String execute() throws Exception {
        System.out.println("action three");
        return null;
    }
}
