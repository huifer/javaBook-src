package com.huifer.security.entity;

import java.util.Objects;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-21
 */
@Entity
@Table(name = "t_role", schema = "at15", catalog = "")
public class RoleEntity {
    private Integer id;
    private String name;
    private UserEntity tUserById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public UserEntity gettUserById() {
        return tUserById;
    }

    public void settUserById(UserEntity tUserById) {
        this.tUserById = tUserById;
    }
}
