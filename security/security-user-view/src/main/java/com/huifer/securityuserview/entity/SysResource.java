package com.huifer.securityuserview.entity;

import javax.persistence.*;

/**
 *  资源类：本实例指的是访问路径
 */
@Entity
public class SysResource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private Integer id;

    @Column(name="resourceName",length=1000)
    private String resourceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return "SysResource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}

