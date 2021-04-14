package com.github.huifer.simple.shiro.boot.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shiro_role", schema = "simple-shiro", catalog = "")
public class ShiroRoleEntity {

  private int id;
  private String name;
  private String code;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  @Basic
  @Column(name = "code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShiroRoleEntity that = (ShiroRoleEntity) o;
    return id == that.id && Objects.equals(name, that.name) && Objects
        .equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, code);
  }
}
