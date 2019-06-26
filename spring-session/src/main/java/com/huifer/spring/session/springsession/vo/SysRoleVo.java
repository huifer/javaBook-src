package com.huifer.spring.session.springsession.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huifer.spring.session.springsession.entity.SysResource;
import java.util.List;
import lombok.Data;

/**
 * <p>Title : SysRoleVo </p>
 * <p>Description : 角色</p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@JsonInclude(Include.NON_NULL)
@Data
public class SysRoleVo {

    private Integer id;
    private String name;
    private Integer level;
    private String note;
    private List<SysResource> resourceList;
}
