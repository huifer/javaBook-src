package com.huifer.spring.session.springsession.table;

import com.huifer.spring.session.springsession.entity.SysRole;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title : SysUserTable </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
public class SysUserTable {

    private Integer id;

    @Length(max = 32)
    @NotEmpty
    private String name;

    @Length(max = 32)
    @NotEmpty
    private String account;


    @NotEmpty
    @Length(max = 32)
    private String password;


    private List<SysRole> sysRoleList;

    @Digits(integer = 1, fraction = 0)
    @Range(min = 0, max = 1)
    private String forbidden;


}
