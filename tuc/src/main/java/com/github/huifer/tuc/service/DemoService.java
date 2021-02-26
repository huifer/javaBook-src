package com.github.huifer.tuc.service;

import com.github.huifer.tuc.model.AbsData;
import com.github.huifer.tuc.model.MiddleRoleAndUserEntity;
import com.github.huifer.tuc.model.RoleEntity;

public abstract class DemoService {

    // BindOperation
    abstract boolean hasBind(Object data, Class<?> clazz);

    // BindOperation
    abstract void createBind(Integer otherId, Integer myId, Class<?> clazz);


    public void handler(AbsData o, Class<?> clazz) {
        if (clazz.equals(RoleEntity.class)) {
            extracted(o, clazz);
        } else if (clazz.equals(MiddleRoleAndUserEntity.class)) {
            extracted(o, clazz);
        }
    }

    private void extracted(AbsData o, Class<?> clazz) {
        if (hasBind(o, clazz)) {
            update(o, clazz);
        } else {
            Integer insert = insert(o, clazz);
            this.createBind(insert, o.getId(), clazz);
        }
    }

    abstract Integer insert(Object data, Class<?> clazz);

    abstract void update(Object data, Class<?> clazz);

//    public static List< AbsData> getDataByType(List<? extends AbsData> list,
//            Class<? extends AbsData> clazz)
//            throws IllegalAccessException, InstantiationException {
//
//        List<AbsData> data = new ArrayList<>(list.size());
//        for (AbsData o : list) {
//            data.add(convert(o, clazz));
//
//        }
//        return data;
//    }
//
//    private static AbsData convert(AbsData o, Class clazz) {
//        boolean equals = clazz.equals(MiddleRoleAndUserEntity.class);
//        if (equals) {
//            MiddleRoleAndUserEntity middleRoleAndUserEntity = new MiddleRoleAndUserEntity();
//            middleRoleAndUserEntity.setRoleId(1);
//            return middleRoleAndUserEntity;
//        }
//        return null;
//    }


}
