package com.github.huifer.ctrpluginexample.api;

public interface InsertOrUpdateConvert<InsType, UpType, EntityType> {

    EntityType fromInsType(InsType insType);

    EntityType fromUpType(UpType upType);

}
