package com.huifer.utils.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: wang
 * @description:
 */
@Data
@ToString
public class PageEntity implements Serializable {

    private static final long serialVersionUID = 3753680813001922109L;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 每页多少条
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页
     */
    private int currPage;

    /**
     * 列表数据
     */
    private List<?> data;

    public PageEntity(int total, int pageSize, int currPage, List<?> data) {
        this.total = total;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil((double) total / pageSize);

        this.currPage = currPage;
        this.data = data;
    }
}