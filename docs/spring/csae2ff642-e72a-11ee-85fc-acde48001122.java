package org.huifer.rbac.entity.req.resource.url;


import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.huifer.rbac.entity.db.TUrl;
import org.huifer.rbac.label.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UrlAddReq implements Convert<TUrl> {
    @NotEmpty(message = "url 地址不能为空")
    private String url;

    @NotEmpty(message = "url 描述信息不能为空")
    private String context;


    @Override
    public TUrl convert() {
        TUrl tUrl = new TUrl();
        tUrl.setContext(this.context);
        tUrl.setCreateTime(LocalDateTime.now());
        tUrl.setUpdateTime(LocalDateTime.now());
        tUrl.setUrl(this.url);
        return tUrl;
    }
}
