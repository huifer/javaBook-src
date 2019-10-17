package com.huifer.struts.action;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-19
 */
public class HelloAction {

    /***
     * 固定写法 ， 不能写任何参数
     * @return
     */
    public String execute() {
        System.out.println("helloAction");
        return "success";
    }
}
