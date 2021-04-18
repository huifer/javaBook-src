package com.github.huifer.full.shiro.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户拓展信息表
 */
@Data
@Entity
@Table(name = "shiro_user_extend")
@EqualsAndHashCode(callSuper = true)
public class ShiroUserExtendEntity extends com.github.huifer.full.shiro.entity.BaseEntry implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  /**
   * 用户id
   */
  @Column(name = "user_id", nullable = false)
  private Integer userId;

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
