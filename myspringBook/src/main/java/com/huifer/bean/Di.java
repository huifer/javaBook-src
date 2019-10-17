package com.huifer.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-02
 */
@Data
@NoArgsConstructor
public class Di {

    private String diName;
    private int[] ints;
    private List<Integer> integerList;
}
