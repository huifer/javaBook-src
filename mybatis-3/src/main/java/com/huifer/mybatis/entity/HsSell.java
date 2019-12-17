package com.huifer.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HsSell {
    /**
    * ID
    */
    private Integer id;

    /**
    * 客户ID
    */
    private Integer userId;

    /**
    * 商品ID
    */
    private Integer goodId;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 购买数量
    */
    private Integer size;

    /**
    * 所属公司ID
    */
    private Integer companyId;

    /**
    * 集团ID
    */
    private Integer groupId;

    /**
    * 版本
    */
    private Integer version;

    /**
    * 删除标志
    */
    private Byte deleted;

    /**
    * 创建用户
    */
    private Integer createUser;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新用户
    */
    private Integer updateUser;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 工单id
    */
    private Integer workOrderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }
}