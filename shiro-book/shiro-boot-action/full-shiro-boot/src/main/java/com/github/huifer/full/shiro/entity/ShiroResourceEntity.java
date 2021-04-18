package com.github.huifer.full.shiro.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资源表
 */
@Data
@Entity
@Table(name = "shiro_resource")
@EqualsAndHashCode(callSuper = true)
public class ShiroResourceEntity extends com.github.huifer.full.shiro.entity.BaseEntry implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * 应用id
   */
  @Column(name = "app_id")
  private Integer appId;

  /**
   * 资源名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 资源类型
   */
  @Column(name = "tyoe")
  private Integer tyoe;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "create_user")
  private Integer createUser;

  @Column(name = "update_user")
  private Integer updateUser;

  @Column(name = "update_time")
  private Date updateTime;

  @Column(name = "version")
  private Integer version;

  @Column(name = "deleted")
  private Integer deleted;

}
