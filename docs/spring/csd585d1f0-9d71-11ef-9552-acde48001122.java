package com.huifer.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryModel {
    private Item item;
    private TestUser user;
}
