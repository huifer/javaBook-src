package com.huifer.hibernatebook.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 描述:
 * 联系人
 *
 * @author huifer
 * @date 2019-02-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkMan {
    /***
     *联系人编号
     */
    private Long lkm_id;
    /***
     *联系人姓名
     */
    private String lkm_name;
    /***
     *联系人性别
     */
    private String lkm_gender;
    /***
     *联系人办公电话
     */
    private String lkm_phone;
    /***
     *联系人手机
     */
    private String lkm_mobile;
    /***
     *联系人邮箱
     */
    private String lkm_email;
    /***
     *联系人qq
     */
    private String lkm_qq;
    /***
     *联系人职位
     */
    private String lkm_position;
    /***
     *联系人备注
     */
    private String lkm_memo;
    // 通过ORM方式表示：一个联系人只能属于某一个客户。
    // 放置的是一的一方的对象。
    private Customer customer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkMan linkMan = (LinkMan) o;
        return Objects.equals(lkm_name, linkMan.lkm_name) &&
                Objects.equals(lkm_gender, linkMan.lkm_gender) &&
                Objects.equals(lkm_phone, linkMan.lkm_phone) &&
                Objects.equals(lkm_mobile, linkMan.lkm_mobile) &&
                Objects.equals(lkm_email, linkMan.lkm_email) &&
                Objects.equals(lkm_qq, linkMan.lkm_qq) &&
                Objects.equals(lkm_position, linkMan.lkm_position) &&
                Objects.equals(lkm_memo, linkMan.lkm_memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lkm_name, lkm_gender, lkm_phone, lkm_mobile, lkm_email, lkm_qq, lkm_position, lkm_memo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"lkm_id\":")
                .append(lkm_id);
        sb.append(",\"lkm_name\":\"")
                .append(lkm_name).append('\"');
        sb.append(",\"lkm_gender\":\"")
                .append(lkm_gender).append('\"');
        sb.append(",\"lkm_phone\":\"")
                .append(lkm_phone).append('\"');
        sb.append(",\"lkm_mobile\":\"")
                .append(lkm_mobile).append('\"');
        sb.append(",\"lkm_email\":\"")
                .append(lkm_email).append('\"');
        sb.append(",\"lkm_qq\":\"")
                .append(lkm_qq).append('\"');
        sb.append(",\"lkm_position\":\"")
                .append(lkm_position).append('\"');
        sb.append(",\"lkm_memo\":\"")
                .append(lkm_memo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}

