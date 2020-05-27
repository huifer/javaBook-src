package org.huifer.rbac.entity.req.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserQueryReq {
    private String username;

    public UserQueryReq(String username) {
        this.username = username;
    }
}
