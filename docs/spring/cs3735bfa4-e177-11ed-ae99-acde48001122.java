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
@Table(name = "shiro_resources", schema = "simple-shiro", catalog = "")
public class ShiroResourcesEntity {

  private int id;
  private String name;
  private String val;

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
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "val")
  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShiroResourcesEntity that = (ShiroResourcesEntity) o;
    return id == that.id && Objects.equals(name, that.name) && Objects
        .equals(val, that.val);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, val);
  }
}
