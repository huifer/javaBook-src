package com.huifer.design.decorate.login;

/**
 * <p>Title : LoginInterface </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public interface LoginInterface {

    ResultMsg regist(String name, String pwd);

    ResultMsg login(String name, String pwd);
}
