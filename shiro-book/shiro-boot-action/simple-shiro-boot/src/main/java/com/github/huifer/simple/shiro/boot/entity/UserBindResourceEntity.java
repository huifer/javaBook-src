package com.github.huifer.simple.shiro.boot.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_bind_resource", schema = "simple-shiro", catalog = "")
public class UserBindResourceEntity {

  private int id;
  private Integer userId;
  private Integer resourceId;

  @Id
  @Column(name = "id")
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
  @Column(name = "resource_id")
  public Integer getResourceId() {
    return resourceId;
  }

  public void setResourceId(Integer resourceId) {
    this.resourceId = resourceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBindResourceEntity that = (UserBindResourceEntity) o;
    return id == that.id && Objects.equals(userId, that.userId) && Objects
        .equals(resourceId, that.resourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, resourceId);
  }
}
