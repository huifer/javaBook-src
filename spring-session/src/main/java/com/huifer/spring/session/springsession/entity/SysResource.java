package com.huifer.spring.session.springsession.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * <p>Title : SysResource </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
@Entity
public class SysResource {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 父级id
     */
    private Integer parentId;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 权限符号
     */
    private String perms;
    /**
     * 资源类型
     */
    private String type;


}
