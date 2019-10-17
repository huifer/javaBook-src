package com.huifer.webflux.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@Data
@Document(collection = "t_student")
public class Student {

    @Id
    private String id;


    @NotBlank(message = "名字不能空")
    private String name;

    @Range(max = 200, min = 18)
    private int age;


}
