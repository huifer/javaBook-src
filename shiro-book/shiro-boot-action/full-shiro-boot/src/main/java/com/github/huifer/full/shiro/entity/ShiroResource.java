package com.github.huifer.full.shiro.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * 资源表(ShiroResource)表实体类
 *
 * @author huifer
 * @since 2021-04-18 10:08:20
 */
@SuppressWarnings("serial")
public class ShiroResource extends Model<ShiroResource> {

  private Integer id;
  //应用id
  private Integer appId;
  //资源名称
  private String name;
  //资源类型
  private Integer tyoe;

  private Date createTime;

  private Integer createUser;

  private Integer updateUser;

  private Date updateTime;

  private Integer version;

  private Integer deleted;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTyoe() {
    return tyoe;
  }

  public void setTyoe(Integer tyoe) {
    this.tyoe = tyoe;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Integer createUser) {
    this.createUser = createUser;
  }

  public Integer getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(Integer updateUser) {
    this.updateUser = updateUser;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
