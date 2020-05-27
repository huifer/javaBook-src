package org.huifer.rbac.entity.req.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.huifer.rbac.entity.db.TUser;
import org.huifer.rbac.label.Convert;
import org.huifer.rbac.utils.Md5Util;

@Data
@EqualsAndHashCode
public class UserAddReq implements Convert<TUser> {

    @Pattern(regexp = "[a-zA-Z]{6,12}", message = "请输入英文作为用户名")
    @Length(min = 6, max = 12, message = "用户名请在 6-12 位之间")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "[a-zA-Z0-9]{6,12}", message = "密码请输入英文和数字作为组合")
    @Length(min = 6, max = 12, message = "密码请在 6-12 位之间")
    private String password;

    @Override
    public TUser convert() {
        TUser tUser = new TUser();
        tUser.setUserName(this.getUsername());
        tUser.setPassword(Md5Util.MD5(this.getPassword()));
        return tUser;
    }
}
