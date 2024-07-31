package com.github.huifer.tuc.model;

public class RoleEntity extends AbsData {

    private Integer id;
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
