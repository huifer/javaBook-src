package org.huifer.rbac.entity.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserAddReq {
    private String name;

    private Integer age;
}
