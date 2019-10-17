package com.huifer.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Long id;
    private String dname;
    private String loc;

    /**
     * 职员集合
     */
    private List<Employee> empList;

}
