package com.github.huifer.full.shiro.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntry implements Serializable {


  public abstract Integer getDeleted();

  public abstract void setDeleted(Integer deleted);

  public abstract Date getCreateTime();

  public abstract void setCreateTime(Date createTime);

  public abstract Integer getVersion();

  public abstract void setVersion(Integer version);

  public abstract Date getUpdateTime();

  public abstract void setUpdateTime(Date updateTime);

  public abstract Integer getCreateUser();

  public abstract void setCreateUser(Integer userId);
}