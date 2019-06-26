package com.huifer.spring.session.springsession.table;

import com.huifer.spring.session.springsession.entity.SysResource;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title : SysRoleTable </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
public class SysRoleTable {

    private Integer id;

    @Length(max = 32)
    @NotEmpty
    private String name;

    @Digits(integer = 2, fraction = 0)
    @Range(min = 1, max = 10)
    private Integer level;

    @Length(max = 1000)
    private String note;

    private List<SysResource> sysResourceList;


}
