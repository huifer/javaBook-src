package org.huifer.rbac.entity.req.resource.menu;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MenuAddReq {
    @NotEmpty(message = "菜单唯一表示不能为空")
    private String name;

    @NotEmpty(message = "菜单类型不能为空")
    private String type;
}
