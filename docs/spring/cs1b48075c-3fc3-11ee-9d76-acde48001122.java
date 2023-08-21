package org.huifer.rbac.entity.req.role;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleBindBtnReq {
    private Long roleId;
    private List<Long> btnIds;
}
