package org.huifer.rbac.entity.req.resource.btn;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BtnEditorReq extends BtnAddReq {
    @NotNull(message = "id 不能为空")
    private Long id;
}
