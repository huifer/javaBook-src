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
 * 岗位角色关系表
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "shiro_post_bind_role")
public class ShiroPostBindRoleEntity extends
    com.github.huifer.full.shiro.entity.BaseEntry implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * 企业id
   */
  @Column(name = "company_id", nullable = false)
  private Integer companyId;

  /**
   * 部门id
   */
  @Column(name = "dept_id", nullable = false)
  private Integer deptId;

  /**
   * 岗位id
   */
  @Column(name = "post_id", nullable = false)
  private Integer postId;

  /**
   * 角色id
   */
  @Column(name = "role_id", nullable = false)
  private Integer roleId;

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
