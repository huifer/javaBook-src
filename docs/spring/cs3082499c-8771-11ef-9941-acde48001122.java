package org.huifer.rbac.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rbac.base_filed")
public class BaseFiled implements Serializable {
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
    private Long version;

    /**
     * 是否删除标记
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_VERSION = "version";

    public static final String COL_IS_DELETE = "is_delete";
}