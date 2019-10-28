package com.huifer.vueadmin.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_sys_user_role")
public class SysUserRole {
    public static final String COL_ROLE_ID = "role_id";
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;
    @TableField(value = "role_id")
    private Integer roleId;
}