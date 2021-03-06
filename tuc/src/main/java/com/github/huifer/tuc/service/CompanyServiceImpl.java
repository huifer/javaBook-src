package com.github.huifer.tuc.service;

import com.github.huifer.tuc.model.CompanyEntity;
import com.github.huifer.tuc.model.MiddleRoleAndUserEntity;
import com.github.huifer.tuc.model.RoleEntity;
import com.github.huifer.tuc.model.UserEntity;
import com.github.huifer.tuc.service.abc.HandlerOperation;
import com.github.huifer.tuc.service.abc.Openmapi;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyServiceImpl implements CompanyService {

    //
    // 1. 新增
    // 1.1 推送一条新增数据对方会给我们一个标记
    // 1.2 标记保存表示这条数据已经推送
    // 2. 更新
    // 2.1 更新的判断依据是数据是否被推送过.
    // 2.2 转换对象然后传递给对方
    @Autowired
    private DemoServiceImpl demoService;

    @Autowired
    private Openmapi openmapi;

    @Override
    public void syncCompanySubUser() {
        List<CompanyEntity> companyEntities = new ArrayList<>();

        for (CompanyEntity companyEntity : companyEntities) {
            Integer companyEntityId = companyEntity.getId();
            List<UserEntity> userEntities = findByCompanyId(companyEntityId);


            for (UserEntity userEntity : userEntities) {
                Integer userEntityId = userEntity.getId();

                List<RoleEntity> roleEntities = findByUserId(userEntityId);
                List<MiddleRoleAndUserEntity> middleRoleAndUserEntities = new ArrayList<>();

                for (RoleEntity roleEntity : roleEntities) {
//                    // 1. 判断是否在本系统数据库中
//                    boolean b = hasRole(roleEntity);
//                    if (b) {
//                        updateRole(roleEntity);
//                    } else {
//                        Integer id = insertRole(roleEntity);
//                        saveRoleAndOthoerId(id, roleEntity);
//                    }

//                    demoService.handler(roleEntity, RoleEntity.class);
                    openmapi.handler(roleEntity);

                }

                for (MiddleRoleAndUserEntity middleRoleAndUserEntity : middleRoleAndUserEntities) {
//                    openmapi.handler(middleRoleAndUserEntity);
//                    demoService.handler(middleRoleAndUserEntity, MiddleRoleAndUserEntity.class);
                  openmapi.handler(middleRoleAndUserEntity);

                    boolean b = hasMiddleRoleAndUserEntity(middleRoleAndUserEntity);
                    if (b) {
                        updateMiddleRoleAndUserEntity(middleRoleAndUserEntity);
                    } else {
                        Integer id = insertMiddleRoleAndUserEntity(middleRoleAndUserEntity);
                        saveMiddleRoleAndUserEntityAndOthoerId(id, middleRoleAndUserEntity);
                    }
                }

            }
        }
    }

    private void saveRoleAndOthoerId(Integer id, RoleEntity roleEntity) {

    }

    private void saveMiddleRoleAndUserEntityAndOthoerId(Integer id,
            MiddleRoleAndUserEntity middleRoleAndUserEntity) {
    }

    private boolean hasMiddleRoleAndUserEntity(MiddleRoleAndUserEntity middleRoleAndUserEntity) {
        return false;
    }

    private void updateMiddleRoleAndUserEntity(MiddleRoleAndUserEntity middleRoleAndUserEntity) {

    }

    private Integer insertMiddleRoleAndUserEntity(MiddleRoleAndUserEntity middleRoleAndUserEntity) {

        return null;
    }

    private Integer insertRole(RoleEntity roleEntity) {

        return null;
    }

    private void updateRole(RoleEntity roleEntity) {

    }

    private boolean hasRole(RoleEntity r) {
        return false;
    }

    private List<RoleEntity> findByUserId(Integer userEntityId) {
        // 通过中间表再去寻找 RoleIds
        return new ArrayList<>();
    }

    private List<UserEntity> findByCompanyId(Integer companyEntityId) {
        return new ArrayList<>();
    }

}
