package com.huifer.design.adapter.login;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : LoginService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class LoginService {

    public static List<Menber> menberList = new ArrayList<>();

    public ResultMsg regist(String name, String pwd) {
        Menber menber = new Menber(name, pwd);
        menberList.add(menber);
        return new ResultMsg(200, menber, name);
    }

    public ResultMsg login(String name, String pwd) {

        Menber m = new Menber(name, pwd);
        if (menberList.contains(m)) {
            return new ResultMsg(200, "登陆成功", name);
        } else {
            return new ResultMsg(400, "登陆失败", name);
        }
    }

}
