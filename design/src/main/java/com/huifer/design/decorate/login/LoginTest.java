package com.huifer.design.decorate.login;

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
        NewLoginService newLoginService = new NewLoginService(loginService);


        ResultMsg resultMsg = newLoginService.loginForQQ("1111111");
        System.out.println(resultMsg);
    }

}
