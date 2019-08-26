package com.huifer.redis.history;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableStatus implements Serializable {

    private static final long serialVersionUID = 7083810667080899691L;
    private String tableName;
    private Integer autoIncrement;
}
