package com.huifer.vueadmin.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_sys_role")
public class SysRole {
    public static final String COL_NAME = "name";
    public static final String COL_DESC = "desc";
    public static final String COL_VAL = "val";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UP_TIME = "up_time";
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 权限中文名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 权限介绍
     */
    @TableField(value = "desc")
    private String desc;
    /**
     * 权限英文
     */
    @TableField(value = "val")
    private String val;
    @TableField(value = "create_time")
    private Integer createTime;
    @TableField(value = "up_time")
    private Integer upTime;
}