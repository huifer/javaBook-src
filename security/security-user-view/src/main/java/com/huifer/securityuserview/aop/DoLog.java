package com.huifer.securityuserview.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoLog implements Serializable {

    private Long id;
    /**
     * asplog value 属性值
     */
    private String aspVal;

    /**
     * 操作的具体类
     */
    private String doClass;
    /**
     * 操作方法
     */
    private String methodName;
    /**
     * 参数
     */
    private Object params;

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}