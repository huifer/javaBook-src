package org.huifer.rbac.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色和菜单的关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rbac.middle_role_menu")
public class MiddleRoleMenu implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 版本
     */
    @TableField(value = "version")
    @Version
    private Long version;

    /**
     * 是否删除标记
     */
    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_VERSION = "version";

    public static final String COL_IS_DELETE = "is_delete";
}