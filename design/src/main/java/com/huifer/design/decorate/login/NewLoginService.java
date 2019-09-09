package com.huifer.design.decorate.login;


/**
 * <p>Title : NewLoginService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class NewLoginService implements NewLoginInterface {

    /**
     * 依旧使用原来的接口来进行注册 登陆操作
     */
    LoginInterface loginInterface;

    public NewLoginService(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }


    @Override
    public ResultMsg loginForQQ(String qqNumb) {
        ResultMsg msg = this.loginInterface.regist(qqNumb, "qq默认密码");

        ResultMsg login = this.loginInterface.login(qqNumb, "qq默认密码");

        return login;
    }
}
