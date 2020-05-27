package org.huifer.rbac.entity.req.user;


import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.huifer.rbac.entity.db.TUser;
import org.huifer.rbac.label.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserBindRoleReq  {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotNull(message = "用户id不能为空")
    @Size(min = 1, message = "至少选择一个角色 ")
    private List<Long> roleIds;
}
