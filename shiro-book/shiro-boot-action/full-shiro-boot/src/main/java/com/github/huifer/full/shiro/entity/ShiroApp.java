package com.github.huifer.full.shiro.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (ShiroApp)表实体类
 *
 * @author HuiFer
 * @since 2021-04-20 09:02:01
 */
@SuppressWarnings("serial")
public class ShiroApp extends Model<ShiroApp> {

  private Integer id;

  private String name;

  private Date createTime;

  private Integer createUser;

  private Integer deleted;

  private Date updateTime;

  private Integer updateUser;

  private Integer version;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(Integer updateUser) {
    this.updateUser = updateUser;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
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
