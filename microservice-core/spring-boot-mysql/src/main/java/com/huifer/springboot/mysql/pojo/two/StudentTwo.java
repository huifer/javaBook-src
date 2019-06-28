package com.huifer.springboot.mysql.pojo.two;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * <p>Title : StudentTwo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-28
 */
@Entity
@Data
@ToString
@Builder
@Table(name = "student_two")
public class StudentTwo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private Integer age;
    private String address;
}
