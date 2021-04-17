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
 * 部门表
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "shiro_dept")
@EqualsAndHashCode(callSuper = true)
public class ShiroDeptEntity extends com.github.huifer.full.shiro.entity.BaseEntry implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * 企业ID
   */
  @Column(name = "company_id")
  private Integer companyId;

  /**
   * 部门名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 部门主管
   */
  @Column(name = "main_user")
  private Integer mainUser;

  /**
   * 上级部门
   */
  @Column(name = "pid")
  private Integer pid;

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
