package com.huifer.jdk.doc;

public class Login {

    /**
     * 登录: 使用账号密码进行登录
     * <ol>
     *     <li>需求文档: 需求文档url</li>
     *     <li>流程文档: 流程文档url</li>
     * </ol>
     * @param username
     * @param password
     * @return
     * @see Login#inSystem(java.lang.String)
     * @version 2
     */
    public Boolean login(String username, String password) {
        // 业务代码
        if (inSystem(username)) {
            giveVip(username);
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * 判断是否为系统内用户
     * @param username 用户名
     * @return true: 是,false: 否
     */
    private boolean inSystem(String username) {
        // 业务代码
        return true;
    }

    /**
     * 赠送VIP
     * @param username
     */
    private void giveVip(String username){
        // 业务代码
    }
}
