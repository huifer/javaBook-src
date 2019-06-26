package com.huifer.spring.session.springsession.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * <p>Title : SysUser </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
@Entity
public class SysUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String account;
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String forbidden;
}
