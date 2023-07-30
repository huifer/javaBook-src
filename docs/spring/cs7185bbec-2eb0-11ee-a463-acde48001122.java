package org.huifer.rbac.entity.req.user;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserEditorReq extends UserAddReq {
    @NotNull(message = "用户id必须选择")
    private Long id;
}
