package org.huifer.rbac.entity.req.resource.btn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BtnQueryReq {
    private String name;

    private String type;

}
