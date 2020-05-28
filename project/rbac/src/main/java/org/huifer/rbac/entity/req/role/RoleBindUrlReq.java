package org.huifer.rbac.entity.req.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleBindUrlReq {
    private Long roleId;
    private List<Long> urlIds;
}
