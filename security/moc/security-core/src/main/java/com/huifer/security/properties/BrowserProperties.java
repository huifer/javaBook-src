package com.huifer.security.properties;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-17
 */
public class BrowserProperties {
    /**
     * // http://localhost:8060/login.html 没有设置
     */
    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
