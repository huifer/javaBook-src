package com.huifer.design.adapter.login;

/**
 * <p>Title : LoginTest </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class LoginTest {

    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        ResultMsg registA = loginService.regist("a", "1");
        System.out.println(registA);
        ResultMsg loginA = loginService.login("a", "1");
        System.out.println(loginA);

        NewLoginService newLoginService = new NewLoginService();
        ResultMsg resultMsg = newLoginService.loginForQQ("QQ登陆测试");
        System.out.println(resultMsg);

        System.out.println(LoginService.menberList);

    }

}
