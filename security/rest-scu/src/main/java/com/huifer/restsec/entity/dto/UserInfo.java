package com.huifer.restsec.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UserInfo {

    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView{}

    private String id;



    @JsonView(UserSimpleView.class)
    private String name;


    @NotBlank
    @JsonView(UserDetailView.class)
    private String pwd;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date registerTime;


    @Override
    public String toString() {
        return "{\"UserInfo\":{"
                + "\"id\":\""
                + id + '\"'
                + ",\"name\":\""
                + name + '\"'
                + ",\"pwd\":\""
                + pwd + '\"'
                + ",\"registerTime\":\""
                + registerTime + '\"'
                + ",\"upTime\":\""
                + upTime + '\"'
                + "}}";

    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date upTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

}
