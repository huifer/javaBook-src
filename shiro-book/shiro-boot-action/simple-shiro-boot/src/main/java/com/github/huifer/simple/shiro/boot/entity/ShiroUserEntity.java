package com.github.huifer.simple.shiro.boot.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shiro_user", schema = "simple-shiro", catalog = "")
public class ShiroUserEntity {

  private int id;
  private String username;
  private String password;
  private String salt;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  @Basic
  @Column(name = "salt")
  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShiroUserEntity that = (ShiroUserEntity) o;
    return id == that.id && Objects.equals(username, that.username) && Objects
        .equals(password, that.password) && Objects.equals(salt, that.salt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, salt);
  }
}
