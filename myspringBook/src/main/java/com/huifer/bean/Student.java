package com.huifer.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 学生
 *
 * @author huifer
 * @date 2019-03-02
 */
@Data
@NoArgsConstructor
public class Student {

    private String sname;
    private int age;
    private Teacher teacher;
}
