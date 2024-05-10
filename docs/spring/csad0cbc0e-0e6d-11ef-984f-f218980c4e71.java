package com.huifer.hibernatebook.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 描述:
 * 用户
 *
 * @author huifer
 * @date 2019-02-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    /***
     *用户id
     */
    private Long user_id;
    /***
     *用户账号
     */
    private String user_code;
    /***
     *用户名称
     */
    private String user_name;
    /***
     *用户密码
     */
    private String user_password;
    /***
     * 用户状态
     *1:正常,0:暂停
     */
    private String user_state;
    // 设置多对多关系：表示一个用户选择多个角色？
    // 放置的是角色的集合
    /***
     * 角色
     */
    private Set<Role> roles = new HashSet<Role>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id) &&
                Objects.equals(user_code, user.user_code) &&
                Objects.equals(user_name, user.user_name) &&
                Objects.equals(user_password, user.user_password) &&
                Objects.equals(user_state, user.user_state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_code, user_name, user_password, user_state);
    }
}
