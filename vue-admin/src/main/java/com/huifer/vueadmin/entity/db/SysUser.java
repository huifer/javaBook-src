package com.huifer.vueadmin.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Set;

@Data
@TableName(value = "t_sys_user")
public class SysUser {
    public static final String COL_USERNAME = "username";
    public static final String COL_PWD = "pwd";
    public static final String COL_NICK = "nick";
    public static final String COL_SALT = "salt";
    public static final String COL_LOCK = "lock";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UP_TIME = "up_time";
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "pwd")
    private String pwd;
    @TableField(value = "nick")
    private String nick;
    @TableField(value = "salt")
    private String salt;
    @TableField(value = "lock")
    private String lock;
    @TableField(value = "create_time")
    private Integer createTime;
    @TableField(value = "up_time")
    private Integer upTime;
    @TableField(exist = false)
    private Set<String> roles;
}