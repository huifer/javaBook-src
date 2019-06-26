package com.huifer.spring.session.springsession.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * <p>Title : SysRoleResource </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
@Entity
public class SysRoleResource {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer roleId;

    private Integer resourceId;
}
