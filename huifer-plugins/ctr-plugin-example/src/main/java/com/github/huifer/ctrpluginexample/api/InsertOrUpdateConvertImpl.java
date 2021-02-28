package com.github.huifer.ctrpluginexample.api;

import com.github.huifer.ctrpluginexample.entity.AppEntity;
import com.github.huifer.ctrpluginexample.req.AppAddParam;

public class InsertOrUpdateConvertImpl implements
        InsertOrUpdateConvert<AppAddParam, AppAddParam, AppEntity> {

    @Override
    public AppEntity fromInsType(AppAddParam appAddParam) {
        AppEntity appEntity = new AppEntity();
        appEntity.setName(appAddParam.getName() + "111");
        return appEntity;
    }

    @Override
    public AppEntity fromUpType(AppAddParam appAddParam) {
        AppEntity appEntity = new AppEntity();

        return appEntity;
    }
}
