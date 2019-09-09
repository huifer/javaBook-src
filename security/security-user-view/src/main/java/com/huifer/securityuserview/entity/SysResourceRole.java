package com.huifer.securityuserview.entity;

/**
 * 资源与角色中间表
 */
@Entity
public class SysResourceRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "roleId", length = 50)
    private String roleId;

    @Column(name = "resourceId", length = 50)
    private String resourceId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "SysResourceRole{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
