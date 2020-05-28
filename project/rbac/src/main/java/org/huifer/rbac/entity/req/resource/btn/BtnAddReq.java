package org.huifer.rbac.entity.req.resource.btn;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.huifer.rbac.entity.db.TBtn;
import org.huifer.rbac.entity.enums.TerminalEnums;
import org.huifer.rbac.label.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BtnAddReq implements Convert<TBtn> {
    @NotEmpty(message = "按钮唯一表示不能为空")
    private String name;

    @NotEmpty(message = "按钮类型不能为空")
    private String type;

    @Override
    public TBtn convert() {
        TBtn tBtn = new TBtn();
        tBtn.setName(this.name);

        List<TerminalEnums> collect = Arrays.stream(TerminalEnums.values()).filter(s -> s.getName().equals(this.type)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            tBtn.setType(this.type);
        }
        else {
            throw new IllegalArgumentException("类型请在 IOS , PC , ANDROID 中选择");
        }
        tBtn.setCreateTime(LocalDateTime.now());
        tBtn.setUpdateTime(LocalDateTime.now());
        return tBtn;
    }
}
