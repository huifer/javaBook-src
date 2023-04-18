package com.huifer.restsec.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class UserInfo {

    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView{}


    @JsonView(UserSimpleView.class)
    private String name;


    @JsonView(UserDetailView.class)
    private String pwd;

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
}
