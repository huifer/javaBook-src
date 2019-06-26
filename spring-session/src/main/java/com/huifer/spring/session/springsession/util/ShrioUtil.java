package com.huifer.spring.session.springsession.util;

import com.huifer.spring.session.springsession.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * <p>Title : ShrioUtil </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
public class ShrioUtil {

    /**
     * 加密方式
     */
    public static final String TYPE = "MD5";
    /**
     * 加密次数
     */

    public static final int count = 10;

    /**
     * md5加密
     *
     * @param pwd 密码
     * @param salt 盐
     * @return 加密结果
     */
    public static String MD5(String pwd, String salt) {
        return new SimpleHash(TYPE, pwd, salt, count).toHex();
    }

    /**
     * 获取subject
     *
     * @return subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取sysUser 实体
     *
     * @return sysuser
     */
    public static SysUser getSysUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取sysUser id
     *
     * @return user id
     */
    public static Integer getSysUserId() {
        return getSysUser().getId();
    }

    /**
     * 获取session
     *
     * @return session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }


    /**
     * 获取session指定key的信息
     */
    public static Object getSessionAttr(Object k) {
        return getSession().getAttribute(k);
    }

    /**
     * 是否登陆
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 随机盐
     */
    public static String getSalt() {
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }
}
