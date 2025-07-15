package com.github.huifer.tuc.model;

import java.util.List;

public class AllUserAttr {

    private UserEntity userEntity;

    private List<MiddleRoleAndUserEntity> middleRoleAndUserEntities;

    private List<RoleEntity> roleEntities;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<MiddleRoleAndUserEntity> getMiddleRoleAndUserEntities() {
        return middleRoleAndUserEntities;
    }

    public void setMiddleRoleAndUserEntities(
            List<MiddleRoleAndUserEntity> middleRoleAndUserEntities) {
        this.middleRoleAndUserEntities = middleRoleAndUserEntities;
    }

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }
}
