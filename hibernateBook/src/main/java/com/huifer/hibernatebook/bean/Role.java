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
 * 角色
 *
 * @author huifer
 * @date 2019-02-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {
    /***
     * id
     */
    private Long role_id;
    /***
     *角色名称
     */
    private String role_name;
    /***
     *备注
     */
    private String role_memo;
    // 一个角色被多个用户选择：
    // 放置的是用户的集合
    /***
     * user列表
     */
    private Set<User> users = new HashSet<User>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(role_id, role.role_id) &&
                Objects.equals(role_name, role.role_name) &&
                Objects.equals(role_memo, role.role_memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role_name, role_memo);
    }
}
