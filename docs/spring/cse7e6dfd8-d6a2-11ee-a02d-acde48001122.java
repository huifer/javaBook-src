package com.huifer.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 职员
 *
 * @author huifer
 * @date 2019-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empNo;
    private String ename;
    private String job;
    private Double sal;
}
