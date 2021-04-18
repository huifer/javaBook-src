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
 * 员工表
 */
@Data
@Entity
@Table(name = "shiro_user")
@EqualsAndHashCode(callSuper = true)
public class ShiroUserEntity extends BaseEntry implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * 登录名
   */
  @Column(name = "login_name", nullable = false)
  private String loginName;

  /**
   * 用户名
   */
  @Column(name = "username", nullable = false)
  private String username;

  /**
   * 密码
   */
  @Column(name = "password", nullable = false)
  private String password;

  /**
   * 盐
   */
  @Column(name = "salt", nullable = false)
  private String salt;

  /**
   * 邮箱
   */
  @Column(name = "email")
  private String email;

  /**
   * 性别
   */
  @Column(name = "gender")
  private Integer gender;

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
