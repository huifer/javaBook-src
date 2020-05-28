package org.huifer.rbac.entity.req.resource.url;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UrlEditorReq extends UrlAddReq {
    @NotNull(message = "url ID不能为空")
    private Long id;
}
