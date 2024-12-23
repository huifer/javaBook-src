package com.huifer.design.adapter.login;

/**
 * <p>Title : NewLoginService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class NewLoginService extends LoginService {

    public ResultMsg loginForQQ(String qqNumb) {

        ResultMsg msg = super.regist(qqNumb, "qq默认密码");

        ResultMsg login = super.login(qqNumb, "qq默认密码");

        return login;
    }

}
