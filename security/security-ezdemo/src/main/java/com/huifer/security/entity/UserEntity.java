package com.huifer.security.entity;

import java.util.Objects;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
@Entity
@Table(name = "t_user", schema = "at15", catalog = "")
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private RoleEntity tRoleById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @OneToOne(mappedBy = "tUserById")
    public RoleEntity gettRoleById() {
        return tRoleById;
    }

    public void settRoleById(RoleEntity tRoleById) {
        this.tRoleById = tRoleById;
    }
}
