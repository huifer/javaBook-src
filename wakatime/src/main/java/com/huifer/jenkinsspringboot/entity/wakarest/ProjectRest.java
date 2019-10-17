package com.huifer.jenkinsspringboot.entity.wakarest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019-09-30
 */
@Data
@ApiModel("项目详细信息REST接口数据")
public class ProjectRest {
    private List<ProjectData> data;

    @Data
    @ApiModel("项目详情")
    public static class ProjectData {
        /**
         * 创建时间
         * <datetime: time commit was synced in ISO 8601 format>
         */
        @ApiModelProperty(value = "创建时间", dataType = "Date")
        private Date created_at;
        @ApiModelProperty(value = "html_escaped_name", dataType = "String")
        private String html_escaped_name;
        @ApiModelProperty(value = "id", dataType = "String")
        private String id;
        @ApiModelProperty(value = "name", dataType = "String")
        private String name;
        @ApiModelProperty(value = "仓库", dataType = "String")
        private String repository;
        @ApiModelProperty(value = "url", dataType = "String")
        private String url;
    }
}
