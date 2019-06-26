package com.huifer.spring.session.springsession.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huifer.spring.session.springsession.entity.SysRole;
import java.util.List;
import lombok.Data;

/**
 * <p>Title : SysUserVo </p>
 * <p>Description : 用户</p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SysUserVo {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 可用性 0否1时
     */
    private String forbidden;
    /**
     * 角色
     */
    private List<SysRole> sysRoleVoList;

}
