package com.huifer.redis.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult implements Serializable {

    private static final long serialVersionUID = -1238228136911669523L;
    private String name;
    private Long ns;

}
