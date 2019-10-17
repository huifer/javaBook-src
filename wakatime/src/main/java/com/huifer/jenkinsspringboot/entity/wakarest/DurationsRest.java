package com.huifer.jenkinsspringboot.entity.wakarest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2019-09-30
 */
@Data
@ApiModel("xxx日期下的开发时间")
public class DurationsRest {
    /**
     * 分支列表
     */
    @ApiModelProperty(value = "分支列表", dataType = "String")
    private String branches;
    /**
     * 数据
     */
    private List<DurationsRestData> data;
    /**
     * 结束时间
     */
    private Date end;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 时区
     */
    private String timezone;

    @lombok.Data
    public static class DurationsRestData {
        /**
         * 创建时间<br/>
         * time when user was created in ISO 8601 format
         */
        private Date created_at;
        /**
         * 光标位置<br/>
         * current cursor column position (optional)
         */
        private String cursorpos;
        /**
         * 持续时间,单位秒<br/>
         * length of time of this duration in seconds
         */
        private BigDecimal duration;
        /**
         * id
         */
        private String id;
        /**
         * 光标在第几行
         * current line row number of cursor (optional)
         */
        private String lineno;
        /**
         * 机器id<br/>
         * unique id of the machine which generated this coding activity
         */
        private String machine_name_id;
        /**
         * 项目名
         */
        private String project;
        /**
         * 开始操作时间
         * float: start of this duration as ISO 8601 UTC datetime; numbers after decimal point are fractions of a second
         */
        private BigDecimal time;

        private String user_id;
    }
}
