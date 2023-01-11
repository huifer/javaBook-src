package org.huifer.rbac.entity.req.resource.url;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UrlQueryReq {
    private String url;

    private String context;
}
