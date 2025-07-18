package com.github.huifer.simple.shiro.boot.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_bind_role", schema = "simple-shiro", catalog = "")
public class UserBindRoleEntity {

  private int id;
  private Integer userId;
  private Integer roleId;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "user_id")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "role_id")
  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBindRoleEntity that = (UserBindRoleEntity) o;
    return id == that.id && Objects.equals(userId, that.userId) && Objects
        .equals(roleId, that.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, roleId);
  }
}
