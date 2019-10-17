package com.huifer.jenkinsspringboot.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-04
 */
@NoArgsConstructor
@Data
public class EchartOption {
    private List<String> x;
    private List<BigDecimal> y;
}
