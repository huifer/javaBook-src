package com.huifer.hibernatebook.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 描述:
 * 客户表
 *
 * @author huifer
 * @date 2019-02-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    public Customer(String cust_name) {
        this.cust_name = cust_name;
    }

    /**
     * 客户编号(主键)
     */
    private Long cust_id;
    /**
     * 客户名称(公司名称)
     */
    private String cust_name;
    /**
     * 客户信息来源
     */
    private String cust_source;
    /**
     * 客户所属行业
     */
    private String cust_industry;
    /**
     * 客户级别
     */
    private String cust_level;
    /**
     * 固定电话
     */
    private String cust_phone;
    /**
     * 移动电话
     */
    private String cust_mobile;


    /**
     * 联系人
     */
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cust_name, customer.cust_name) &&
                Objects.equals(cust_source, customer.cust_source) &&
                Objects.equals(cust_industry, customer.cust_industry) &&
                Objects.equals(cust_level, customer.cust_level) &&
                Objects.equals(cust_phone, customer.cust_phone) &&
                Objects.equals(cust_mobile, customer.cust_mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cust_name, cust_source, cust_industry, cust_level, cust_phone, cust_mobile);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"linkMans\":")
                .append(linkMans);
        sb.append("\"cust_id\":")
                .append(cust_id);
        sb.append(",\"cust_name\":\"")
                .append(cust_name).append('\"');
        sb.append(",\"cust_source\":\"")
                .append(cust_source).append('\"');
        sb.append(",\"cust_industry\":\"")
                .append(cust_industry).append('\"');
        sb.append(",\"cust_level\":\"")
                .append(cust_level).append('\"');
        sb.append(",\"cust_phone\":\"")
                .append(cust_phone).append('\"');
        sb.append(",\"cust_mobile\":\"")
                .append(cust_mobile).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
