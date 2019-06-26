package com.huifer.spring.session.springsession.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

/**
 * <p>Title : ResultVo </p>
 * <p>Description : 响应结果</p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Data
@JsonInclude(Include.NON_NULL)
@Builder
public class ResultVo<T> {

    /**
     * 状态吗
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * data
     */
    private T data;

}
