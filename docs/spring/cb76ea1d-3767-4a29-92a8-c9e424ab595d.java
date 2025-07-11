package org.huifer.rbac.entity.db;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rbac.demo")
public class Demo implements Serializable {
    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_VERSION = "version";

    public static final String COL_IS_DELETE = "is_delete";

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @Version
    @TableField(value = "version")
    private Long version;

    @TableField(value = "is_delete")
    @TableLogic
    private Integer isDelete;
}