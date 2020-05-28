package org.huifer.rbac.entity.req.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleEditorReq extends RoleAddReq {
    @NotNull(message = "角色id必须选择")
    private Long id;
}
