package com.huifer.jenkinsspringboot.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-04
 */
@Data
@NoArgsConstructor
public class ProInfo {
    private String name;
    private BigDecimal totalSeconds;
}
